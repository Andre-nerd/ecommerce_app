package com.zoom_machine.ecommerce_app.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.zoom_machine.ecommerce_app.R
import com.zoom_machine.ecommerce_app.databinding.FragmentMainScreenBinding
import com.zoom_machine.ecommerce_app.presentation.adapters.TopMenuAdapter

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private val viewModel: MainScreenViewModel by viewModels()
    lateinit var binding: FragmentMainScreenBinding
    lateinit var topMenuAdapter: TopMenuAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainScreenBinding.bind(view)
        createAdapter()
        observeViewModel()
        lifecycleScope.launchWhenStarted {
            viewModel.getContent()
        }
    }

    private fun observeViewModel() {
        viewModel.itemTopMenu.observe(viewLifecycleOwner) { listItems ->
            topMenuAdapter.update(listItems)
        }
    }

    private fun createAdapter() {
        topMenuAdapter = TopMenuAdapter(requireContext()) { position ->
            viewModel.handlingClickOnTopMenu(position)
        }
        with(binding.topMenuRecyclerView) {
            adapter = topMenuAdapter
            setHasFixedSize(true)
        }
    }
}

