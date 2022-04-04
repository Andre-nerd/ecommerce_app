package com.zoom_machine.feature_cartscreen.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.zoom_machine.core.utils.MessageViewModel
import com.zoom_machine.feature_cartscreen.R
import com.zoom_machine.feature_cartscreen.databinding.FragmentCartBinding
import com.zoom_machine.feature_cartscreen.presentation.adapters.PurchasesAdapter
import com.zoom_machine.feature_cartscreen.presentation.di.CartScreenComponentViewModel
import com.zoom_machine.nanigation.navigate
import dagger.Lazy
import javax.inject.Inject

class CartFragment : Fragment(R.layout.fragment_cart) {
    @Inject
    internal lateinit var cartScreenViewModelFactory: Lazy<CartViewModel.Factory>
    private val viewModel: CartViewModel by viewModels {

        cartScreenViewModelFactory.get()
    }
    private var binding: FragmentCartBinding? = null
    private val purchasesAdapter by lazy {
        PurchasesAdapter(::onItemClick).apply {
            binding?.purchasesRecyclerView?.adapter = this
            binding?.purchasesRecyclerView?.setHasFixedSize(true)
        }
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<CartScreenComponentViewModel>()
            .newDetailComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
        observeViewModel()
        binding!!.buttonBackArrow.setOnClickListener {
            navigate(R.id.action_cartFragment_to_detailsFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.run {
            total.observe(viewLifecycleOwner) {
                binding?.textTotalSum?.text = "$" + String.format("%.2f", it) + " us"
            }
            purchases.observe(viewLifecycleOwner) {
                purchasesAdapter.update(it)
            }
            deliveryStatus.observe(viewLifecycleOwner) {
                binding?.textDeliveryStatus?.text = it
            }
            throwableMessage.observe(viewLifecycleOwner) { message ->
                handlingThrowableMessage(message)
            }
            showProgressBar.observe(viewLifecycleOwner) {
                binding?.progressBar?.isVisible = it
            }
        }
    }

    private fun handlingThrowableMessage(message: MessageViewModel) {
        when (message) {
            MessageViewModel.CONNECTION_ERROR -> Toast.makeText(
                requireContext(),
                resources.getString(R.string.server_connection_error),
                Toast.LENGTH_LONG
            ).show()
            else -> {}
        }
    }

    private fun onItemClick(position: Int, countPurchase: Int) {
        viewModel.setCountToItem(position, countPurchase)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}