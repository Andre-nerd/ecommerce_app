package com.zoom_machine.ecommerce_app.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zoom_machine.ecommerce_app.R
import com.zoom_machine.ecommerce_app.databinding.FragmentMainScreenBinding
import com.zoom_machine.ecommerce_app.presentation.adapters.BestSellerAdapter
import com.zoom_machine.ecommerce_app.presentation.adapters.HotSaleAdapter
import com.zoom_machine.ecommerce_app.presentation.adapters.TopMenuAdapter
import com.zoom_machine.ecommerce_app.presentation.di.ViewModelAssistedFactory
import com.zoom_machine.ecommerce_app.presentation.utils.MessageViewModel
import com.zoom_machine.ecommerce_app.presentation.utils.appComponent
import javax.inject.Inject

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    @Inject
    lateinit var viewModelAssistedFactory: ViewModelAssistedFactory
    private val viewModel: MainScreenViewModel by activityViewModels {
        viewModelAssistedFactory.create()
    }

    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var topMenuAdapter: TopMenuAdapter
    private lateinit var hotSaleAdapter: HotSaleAdapter
    private lateinit var bestSellerAdapter: BestSellerAdapter

    override fun onAttach(context: Context) {
        requireContext().appComponent.injectMainScreenFragment(this)
        super.onAttach(context)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainScreenBinding.bind(view)
        createTopMenuAdapter()
        createHotSaleAdapter()
        createBestSellerAdapter()
        observeViewModel()
        binding.run {
            buttonOpenFilter.setOnClickListener {
                viewModel.changeFilterVisible()
            }
            buttonDoneFilter.setOnClickListener {
                viewModel.changeFilterVisible()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.run {
            itemTopMenu.observe(viewLifecycleOwner) { listItems ->
                topMenuAdapter.update(listItems)
            }
            hotSales.observe(viewLifecycleOwner) { listHotSale ->
                hotSaleAdapter.update(listHotSale)
            }
            bestSeller.observe(viewLifecycleOwner) { listBestSeller ->
                bestSellerAdapter.update(listBestSeller)
            }
            throwableMessage.observe(viewLifecycleOwner) { message ->
                handlingThrowableMessage(message)
            }
            showProgressBar.observe(viewLifecycleOwner) {
                binding.progressBarPhones.isVisible = it
            }
            statusFilter.observe(viewLifecycleOwner) { status ->
                visibleFilterSetting(status)
                binding.scrollMainScreen.fullScroll(ScrollView.FOCUS_DOWN)
            }
        }
    }

    private fun createTopMenuAdapter() {
        topMenuAdapter = TopMenuAdapter(requireContext()) { position ->
            viewModel.handlingClickOnTopMenu(position)
        }
        with(binding.topMenuRecyclerView) {
            adapter = topMenuAdapter
            setHasFixedSize(true)
        }
    }

    private fun createHotSaleAdapter() {
        hotSaleAdapter = HotSaleAdapter {
        }
        with(binding.hotSaleRecyclerView) {
            adapter = hotSaleAdapter
            setHasFixedSize(true)
        }
    }

    private fun createBestSellerAdapter() {
        bestSellerAdapter = BestSellerAdapter(requireContext()) {
        }
        with(binding.bestSellerRecyclerView) {
            adapter = bestSellerAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
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

    private fun visibleFilterSetting(value: Boolean) {
        binding.run {
            textBrand.isVisible = value
            spinnerBrandFilter.isVisible = value
            textPriceFilter.isVisible = value
            spinnerPrice.isVisible = value
            textSizeFilter.isVisible = value
            spinnerSize.isVisible = value
            buttonDoneFilter.isVisible = value
            if (value) {
                buttonOpenFilter.setText(resources.getString(R.string.close))
            } else {
                buttonOpenFilter.setText(resources.getString(R.string.plus))
            }
        }
    }
}

