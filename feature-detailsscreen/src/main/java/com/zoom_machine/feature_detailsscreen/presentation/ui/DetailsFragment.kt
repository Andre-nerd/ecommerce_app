package com.zoom_machine.feature_detailsscreen.presentation.ui

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.zoom_machine.feature_detailsscreen.R
import com.zoom_machine.feature_detailsscreen.databinding.FragmentDetailsBinding
import com.zoom_machine.feature_detailsscreen.presentation.adapters.TopGalleryAdapter
import com.zoom_machine.feature_detailsscreen.presentation.adapters.ViewPagerAdapter

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
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.run {
            product.observe(viewLifecycleOwner) {
                topGalleryAdapter.update(it.images)
                setColorDevice(it.color)
                setFavoriteButton(it.isFavorites ?: false)
                binding.run {
                    raiting.setRating(it.rating)
                    textTitleModel.text = it.title
                    if (it.isFavorites == true) {
                        buttonFavorite.background = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_batton_favorite_full
                        )
                    }
                }
            }
            specification.observe(viewLifecycleOwner) {
                viewPagerAdapter.update(it)
                attachTabMediator()
            }
        }
    }

    private fun attachTabMediator() {
        val namesTab = resources.getStringArray(R.array.namesTab)
        TabLayoutMediator(binding.tabLayout, binding.viewPagerAdapter) { tab, position ->
            tab.text = namesTab[position]
        }.attach()
    }

    private fun setColorDevice(color: List<String>) {
        val colorFirst = Color.parseColor(color[0].replace("0x", "#", true))
        binding.firstColor.setColorFilter(colorFirst, PorterDuff.Mode.MULTIPLY)
        val colorSecond = Color.parseColor(color[1].replace("0x", "#", true))
        binding.secondColor.setColorFilter(colorSecond, PorterDuff.Mode.MULTIPLY)
    }

    private fun setFavoriteButton(value: Boolean) {
        val drawable = if (value) {
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_batton_favorite_full)
        } else {
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_batton_favorite)
        }
        binding.buttonFavorite.background = drawable
    }
}
