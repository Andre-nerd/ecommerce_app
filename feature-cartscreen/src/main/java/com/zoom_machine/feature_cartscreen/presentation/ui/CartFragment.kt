package com.zoom_machine.feature_cartscreen.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zoom_machine.feature_cartscreen.R
import com.zoom_machine.feature_cartscreen.databinding.FragmentCartBinding
import com.zoom_machine.feature_cartscreen.presentation.adapters.PurchasesAdapter
import com.zoom_machine.feature_cartscreen.presentation.utils.formatFloat
import com.zoom_machine.nanigation.navigate

class CartFragment : Fragment(R.layout.fragment_cart) {
    private val viewModel:CartViewModel by viewModels()
    private lateinit var binding: FragmentCartBinding
    private val purchasesAdapter by lazy {
        PurchasesAdapter(::onItemClick).apply {
            binding.purchasesRecyclerView.adapter = this
            binding.purchasesRecyclerView.setHasFixedSize(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
        observeViewModel()
        binding.buttonBackArrow.setOnClickListener {
            navigate(R.id.action_cartFragment_to_detailsFragment)
        }
    }

    private fun observeViewModel(){
        viewModel.run {
            total.observe(viewLifecycleOwner){
                binding.textTotalSum.text = "$"+String.format("%.2f", it)+" us"
            }
            purchases.observe(viewLifecycleOwner){
                purchasesAdapter.update(it)
            }
        }
    }

    private fun onItemClick(position: Int, countPurchase: Int){
        viewModel.setCountToItem(position,countPurchase)
    }
}