package com.zoom_machine.feature_detailsscreen.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zoom_machine.feature_detailsscreen.R

class ButtonAddCartContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val price: TextView
    private val button: ImageView
    private val mutableClickAddCart = MutableLiveData<Boolean>()
    val clickAddCart: LiveData<Boolean>
        get() = mutableClickAddCart

    init {
        val root = inflate(context, R.layout.container_add_cart, this)
        price = root.findViewById(R.id.textPrice)
        button = root.findViewById(R.id.buttonAddCart)
        button.setColorFilter(context.getColor(R.color.ocher))
        root.rootView.setOnClickListener {
            mutableClickAddCart.value = true
        }
    }

    fun setPrice(value: Float) {
        price.text = formatFloat(value)
    }

    private fun formatFloat(value: Float): String {
        val s = value.toString()
        val th = value % 1000f
        return s[0] + " " + String.format("%.2f", th)
    }
}