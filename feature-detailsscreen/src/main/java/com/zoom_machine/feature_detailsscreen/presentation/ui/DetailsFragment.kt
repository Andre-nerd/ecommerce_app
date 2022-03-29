package com.zoom_machine.feature_detailsscreen.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.zoom_machine.api.services.data.ProductDetails
import com.zoom_machine.feature_detailsscreen.R
import com.zoom_machine.feature_detailsscreen.databinding.FragmentDetailsBinding
import com.zoom_machine.feature_detailsscreen.presentation.adapters.TopGalleryAdapter
import com.zoom_machine.feature_detailsscreen.presentation.adapters.ViewPagerAdapter
import com.zoom_machine.feature_detailsscreen.presentation.utils.FIRST_CAPACITY
import com.zoom_machine.feature_detailsscreen.presentation.utils.GB
import com.zoom_machine.feature_detailsscreen.presentation.utils.SECOND_CAPACITY

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private val topGalleryAdapter by lazy {
        TopGalleryAdapter().apply {
            binding.topGalleryRecyclerView.adapter = this
            binding.topGalleryRecyclerView.setHasFixedSize(true)
        }
    }
    private val viewPagerAdapter by lazy {
        ViewPagerAdapter().apply {
            binding.viewPagerAdapter.adapter = this
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        lifecycleScope.launchWhenStarted {
            viewModel.getDetailsProduct()
        }
        binding.run {
            buttonFavorite.setOnClickListener {
                viewModel.setFavorite()
            }
            buttonBags.setOnClickListener {
                showToast()
            }
            buttonBackArrow.setOnClickListener {
                showToast()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.run {
            product.observe(viewLifecycleOwner) { details ->
                setDetailsToScreenFields(details)
            }
            colorDevice.observe(viewLifecycleOwner) {
                binding.selectColor.switchColor()
            }
            specification.observe(viewLifecycleOwner) {
                viewPagerAdapter.update(it)
                attachTabMediator()
            }
            showProgressBar.observe(viewLifecycleOwner) {
                binding.progressBar.isVisible = it
            }
            capacity.observe(viewLifecycleOwner) {
                setActiveDeviceCapacity()
            }
        }
        binding.run {
            firstCapacity.isActive.observe(viewLifecycleOwner) {
                viewModel.setCapacity(FIRST_CAPACITY)
            }
            secondCapacity.isActive.observe(viewLifecycleOwner) {
                viewModel.setCapacity(SECOND_CAPACITY)
            }
            selectColor.whichColor.observe(viewLifecycleOwner) {
                viewModel.setColorDevice(it)
            }
            addCartButton.clickAddCart.observe(viewLifecycleOwner){
                showToast()
            }
        }
    }

    private fun attachTabMediator() {
        val namesTab = resources.getStringArray(R.array.namesTab)
        TabLayoutMediator(binding.tabLayout, binding.viewPagerAdapter) { tab, position ->
            tab.text = namesTab[position]
        }.attach()
    }

    private fun setDetailsToScreenFields(details: ProductDetails) {
        topGalleryAdapter.update(details.images)
        binding.selectColor.setDeviceColor(details.color)
        setFavoriteButton(details.isFavorites ?: false)
        setDeviceCapacity(details.capacity)
        binding.run {
            raiting.setRating(details.rating)
            textTitleModel.text = details.title
            addCartButton.setPrice(details.price)
        }
    }

    private fun setFavoriteButton(value: Boolean) {
        val drawable = if (value) {
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_batton_favorite_full)
        } else {
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_batton_favorite)
        }
        binding.buttonFavorite.background = drawable
    }

    private fun setDeviceCapacity(capacity: List<String>) {
        binding.run {
            firstCapacity.setText(capacity[FIRST_CAPACITY] + GB)
            secondCapacity.setText(capacity[SECOND_CAPACITY] + GB)
        }
    }

    private fun setActiveDeviceCapacity() {
        binding.run {
            when (viewModel.capacity.value) {
                FIRST_CAPACITY -> {
                    firstCapacity.setActiveStatus(true)
                    secondCapacity.setActiveStatus(false)
                }
                SECOND_CAPACITY -> {
                    firstCapacity.setActiveStatus(false)
                    secondCapacity.setActiveStatus(true)
                }
            }
        }
    }

    private fun showToast(){
        Toast.makeText(requireContext(),"Click",Toast.LENGTH_SHORT).show()
    }
}

