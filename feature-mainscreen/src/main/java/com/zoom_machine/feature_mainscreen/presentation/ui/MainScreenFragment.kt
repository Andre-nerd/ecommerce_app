package com.zoom_machine.feature_mainscreen.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zoom_machine.feature_mainscreen.R
import com.zoom_machine.feature_mainscreen.data.ColorSettingTopMenu
import com.zoom_machine.feature_mainscreen.data.TopMenuItem
import com.zoom_machine.feature_mainscreen.databinding.FragmentMainScreenCopyBinding
import com.zoom_machine.feature_mainscreen.presentation.adapters.BestSellerAdapter
import com.zoom_machine.feature_mainscreen.presentation.adapters.HotSaleAdapter
import com.zoom_machine.feature_mainscreen.presentation.adapters.TopMenuAdapter
import com.zoom_machine.feature_mainscreen.presentation.di.MainScreenComponentViewModel
import com.zoom_machine.feature_mainscreen.presentation.utils.*
import dagger.Lazy
import javax.inject.Inject


class MainScreenFragment @Inject constructor() : Fragment(R.layout.fragment_main_screen_copy) {

    @Inject
    internal lateinit var mainScreenViewModelFactory: Lazy<MainScreenViewModel.Factory>
    private val viewModel: MainScreenViewModel by viewModels {
        mainScreenViewModelFactory.get()
    }
    private lateinit var binding: FragmentMainScreenCopyBinding
    private val topMenuAdapter by lazy {
        TopMenuAdapter(getColorsTopMenu()) { position ->
            viewModel.handlingClickOnTopMenu(position)
        }.apply {
            binding.topMenuRecyclerView.adapter = this
            binding.topMenuRecyclerView.setHasFixedSize(true)
        }
    }
    private val hotSaleAdapter by lazy {
        HotSaleAdapter {
        }.apply {
            binding.hotSaleRecyclerView.adapter = this
            binding.hotSaleRecyclerView.setHasFixedSize(true)
        }
    }

    private val bestSellerAdapter by lazy {
        BestSellerAdapter(
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_full_heart)
        ) {
        }.apply {
            binding.bestSellerRecyclerView.adapter = this
            binding.bestSellerRecyclerView.layoutManager = GridLayoutManager(
                requireContext(), 2, LinearLayoutManager.VERTICAL, false
            )
            binding.bestSellerRecyclerView.setHasFixedSize(true)
        }
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<MainScreenComponentViewModel>()
            .newDetailComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainScreenCopyBinding.bind(view)
        viewModel.setItemsTopMenu(getItemsTopMenu())
        observeViewModel()
        binding.run {
            selectCategoryBlock.apply {
                setTextToCategory(SELECT_CATEGORY)
                setTextToRightMenu(VIEW_ALL)
            }
            bestSellerBlock.apply {
                setTextToCategory(BEST_SELLER)
                setTextToRightMenu(SEE_MORE)
            }
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

    private fun getColorsTopMenu(): ColorSettingTopMenu {
        return ColorSettingTopMenu(
            backgroundSelected = ContextCompat.getColor(requireContext(), R.color.ocher),
            icoSelected = ContextCompat.getColor(requireContext(), R.color.white),
            backgroundUnSelected = ContextCompat.getColor(requireContext(), R.color.white),
            icoUnSelected = ContextCompat.getColor(requireContext(), R.color.light_grey)
        )
    }

    private fun getItemsTopMenu(): List<TopMenuItem> {
        val listItem = emptyList<TopMenuItem>().toMutableList()
        val listResources = getListResources()
        val context = requireContext()
        listResources.forEach {
            val item = TopMenuItem(
                ContextCompat.getDrawable(context, it.first),
                resources.getString(it.second)
            )
            listItem += item
        }
        return listItem
    }
}

