package com.zoom_machine.feature_mainscreen.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.zoom_machine.feature_mainscreen.R
import com.zoom_machine.feature_mainscreen.presentation.utils.SingleLiveEvent

@SuppressLint("CutPasteId")
class CategoryFilterBottom @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    val isOpenFilterPressed = SingleLiveEvent<Boolean>()
    val isDoneFilterPressed = SingleLiveEvent<Boolean>()
    private val textBrand: TextView
    private val spinnerBrandFilter: Spinner
    private val textPriceFilter: TextView
    private val spinnerPrice: Spinner
    private val textSizeFilter: TextView
    private val spinnerSize: Spinner
    private val buttonDoneFilter: androidx.appcompat.widget.AppCompatButton
    private val buttonOpenFilter: androidx.appcompat.widget.AppCompatButton

    init {
        val root = inflate(context, R.layout.container_filter_bottom, this)
        textBrand = root.findViewById(R.id.textBrand)
        spinnerBrandFilter = root.findViewById(R.id.spinnerBrandFilter)
        textPriceFilter = root.findViewById(R.id.textPriceFilter)
        spinnerPrice = root.findViewById(R.id.spinnerPrice)
        textSizeFilter = root.findViewById(R.id.textSizeFilter)
        spinnerSize = root.findViewById(R.id.spinnerSize)
        buttonDoneFilter = root.findViewById(R.id.buttonDoneFilter)
        buttonOpenFilter = root.findViewById(R.id.buttonOpenFilter)

        buttonOpenFilter.setOnClickListener {
            isOpenFilterPressed.value = true
        }
        buttonDoneFilter.setOnClickListener {
            isDoneFilterPressed.value = true
        }
    }

    fun visibleFilterSetting(value: Boolean) {
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