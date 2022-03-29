package com.zoom_machine.feature_detailsscreen.presentation.ui

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zoom_machine.feature_detailsscreen.R
import com.zoom_machine.feature_detailsscreen.presentation.utils.SingleLiveEvent

class CapacityButtonContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val background: ImageView
    private val text: TextView
    private var isActiveMutable = MutableLiveData(false)
    val isActive = SingleLiveEvent<Boolean>()

    init {
        val root = inflate(context, R.layout.container_capacity_button, this)
        background = root.findViewById(R.id.backgroundId)
        text = root.findViewById(R.id.textId)
        root.rootView.setOnClickListener {
            changeActiveStatus()
        }
    }

    fun changeActiveStatus() {
        val active = !(isActive.value ?: false)
        setColorToItems(active)
        isActive.value = true
//        isActiveMutable.postValue(active)
    }

    fun setActiveStatus(value: Boolean) {
        setColorToItems(value)
//        isActiveMutable.postValue(value)
    }

    fun setText(value: String) {
        text.setText(value)
    }

    private fun setColorToItems(value: Boolean) {
        if (value) {
            background.setColorFilter(context.getColor(R.color.ocher))
            text.setTextColor(Color.WHITE)
        } else {
            text.setTextColor(Color.BLACK)
            background.setColorFilter(Color.WHITE)
        }
    }
}