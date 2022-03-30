package com.zoom_machine.feature_cartscreen

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PlusMinusContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val plus: ImageView
    private val minus: ImageView
    private val digit: TextView
    private val mutableCount = MutableLiveData<Int>(1)
    val count: LiveData<Int>
        get() = mutableCount

    init {
        val root = inflate(context, R.layout.container_plus_minus, this)
        plus = root.findViewById(R.id.plus)
        minus = root.findViewById(R.id.minus)
        digit = root.findViewById(R.id.digit)
        plus.setOnClickListener {
            plusCount()
            setDigit(count.value!!)
        }
        minus.setOnClickListener {
            minusCount()
            setDigit(count.value!!)
        }
    }

    fun setDigit(value: Int) {
        digit.text = value.toString()
    }

    private fun plusCount(){
        if(count.value!! < 10) mutableCount.value = mutableCount.value?.plus(1)
    }

    private fun minusCount(){
        if(count.value!! > 0) mutableCount.value = mutableCount.value?.minus(1)
    }
}