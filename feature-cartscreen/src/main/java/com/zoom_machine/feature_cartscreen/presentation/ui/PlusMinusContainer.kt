package com.zoom_machine.feature_cartscreen.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zoom_machine.feature_cartscreen.R

class PlusMinusContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    val plus: ImageView
    val minus: ImageView
    private val digit: TextView
    private val mutableCount = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = mutableCount

    init {
        val root = inflate(context, R.layout.container_plus_minus, this)
        plus = root.findViewById(R.id.plus)
        minus = root.findViewById(R.id.minus)
        digit = root.findViewById(R.id.digit)
    }

    fun setDigit(value: Int) {
        digit.text = value.toString()
        mutableCount.value = value
    }
}