package com.zoom_machine.feature_mainscreen.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.zoom_machine.feature_mainscreen.R
import com.zoom_machine.feature_mainscreen.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow


class CategoryContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val category: TextView
    private val rightMenu: TextView

    val isCategoryPressed = SingleLiveEvent<Boolean>()
    val isRightMenuPressed = SingleLiveEvent<Boolean>()
    private val isCategoryFlowMutable = MutableStateFlow(false)

    init {
        val root = inflate(context, R.layout.container_category, this)
        category = root.findViewById(R.id.textCategoryName)
        rightMenu = root.findViewById(R.id.textRightMenuName)

        category.setOnClickListener {
            isCategoryPressed.value = true
            isCategoryFlowMutable.value = true
            isCategoryFlowMutable.value = false
        }
        rightMenu.setOnClickListener {
            isRightMenuPressed.value = true
        }
    }

    fun setTextToCategory(text: String) {
        category.text = text
    }

    fun setTextToRightMenu(text: String) {
        rightMenu.text = text
    }
}