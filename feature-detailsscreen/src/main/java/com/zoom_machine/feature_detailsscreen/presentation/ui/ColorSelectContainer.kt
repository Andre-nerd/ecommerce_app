package com.zoom_machine.feature_detailsscreen.presentation.ui

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zoom_machine.feature_detailsscreen.R
import com.zoom_machine.feature_detailsscreen.presentation.utils.FIRST_COLOR
import com.zoom_machine.feature_detailsscreen.presentation.utils.SECOND_COLOR

class ColorSelectContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val firstColor: ImageView
    private val secondColor: ImageView
    private val firstOk: ImageView
    private val secondOk: ImageView
    private val mutableWhichColor = MutableLiveData(0)
    val whichColor: LiveData<Int>
        get() = mutableWhichColor

    init {
        val root = inflate(context, R.layout.container_color_select, this)
        firstColor = root.findViewById(R.id.firstColor)
        secondColor = root.findViewById(R.id.secondColor)
        firstOk = root.findViewById(R.id.firstOk)
        secondOk = root.findViewById(R.id.secondOk)
        firstColor.setOnClickListener {
            mutableWhichColor.value = FIRST_COLOR
        }
        secondColor.setOnClickListener {
            mutableWhichColor.value = SECOND_COLOR
        }
    }

    fun setDeviceColor(color: List<String>) {
        val colorFirst = Color.parseColor(color[0].replace("0x", "#", true))
        firstColor.setColorFilter(colorFirst, PorterDuff.Mode.MULTIPLY)
        val colorSecond = Color.parseColor(color[1].replace("0x", "#", true))
        secondColor.setColorFilter(colorSecond, PorterDuff.Mode.MULTIPLY)
    }

    fun switchColor() {
        when (whichColor.value) {
            FIRST_COLOR -> {
                firstOk.visibility = View.VISIBLE
                secondOk.visibility = View.GONE
            }
            SECOND_COLOR -> {
                firstOk.visibility = View.GONE
                secondOk.visibility = View.VISIBLE
            }
        }
    }
}