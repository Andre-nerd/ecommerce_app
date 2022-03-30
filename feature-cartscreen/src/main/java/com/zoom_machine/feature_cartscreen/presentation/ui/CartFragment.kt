package com.zoom_machine.feature_cartscreen.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.zoom_machine.feature_cartscreen.R
import com.zoom_machine.feature_cartscreen.databinding.FragmentCartBinding
import com.zoom_machine.feature_cartscreen.presentation.adapters.PurchasesAdapter

class CartFragment : Fragment(R.layout.fragment_cart) {
    private lateinit var binding: FragmentCartBinding
    private val productAdapter by lazy {
        PurchasesAdapter(::onItemClick).apply {
            binding.purchasesRecyclerView.adapter = this
            binding.purchasesRecyclerView.setHasFixedSize(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
        productAdapter.update(listOf("","",""))
    }

    private fun onItemClick(position: Int, countPurchase: Int){
        Log.d("NEWAPI","position = $position  countPurchase = $countPurchase")
    }
}