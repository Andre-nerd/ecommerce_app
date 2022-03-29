package com.zoom_machine.feature_detailsscreen.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
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
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.run {
            product.observe(viewLifecycleOwner) {
                topGalleryAdapter.update(it.images)
                binding.raiting.setRating(it.rating)
                Log.d("NEWAPI", " response = ${it.title}")
            }
            specification.observe(viewLifecycleOwner) {
                viewPagerAdapter.update(it)
                attachTabMediator()
            }
        }
    }

    private fun attachTabMediator(){
        val namesTab = resources.getStringArray(R.array.namesTab)
        TabLayoutMediator(binding.tabLayout, binding.viewPagerAdapter) { tab, position ->
            tab.text = namesTab[position]
        }.attach()
    }
}