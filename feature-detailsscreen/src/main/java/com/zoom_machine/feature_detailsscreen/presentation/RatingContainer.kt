package com.zoom_machine.feature_detailsscreen.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.zoom_machine.feature_detailsscreen.R

class RatingContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val star1: ImageView
    private val starZeroHalf: ImageView
    private val star2: ImageView
    private val starOneHalf: ImageView
    private val star3: ImageView
    private val starTwoHalf: ImageView
    private val star4: ImageView
    private val starThreeHalf: ImageView
    private val star5: ImageView
    private val starFourHalf: ImageView

    init {
        val root = inflate(context, R.layout.container_rating_stars, this)
        star1 = root.findViewById(R.id.star1)
        starZeroHalf = root.findViewById(R.id.star0_5)
        star2 = root.findViewById(R.id.star2)
        starOneHalf = root.findViewById(R.id.star1_5)
        star3 = root.findViewById(R.id.star3)
        starTwoHalf = root.findViewById(R.id.star2_5)
        star4 = root.findViewById(R.id.star4)
        starThreeHalf = root.findViewById(R.id.star3_5)
        star5 = root.findViewById(R.id.star5)
        starFourHalf = root.findViewById(R.id.star4_5)
    }

    fun setRating(value: Float) {

        if (value >= 0.5F) starZeroHalf.visibility = View.VISIBLE
        if (value >= 1F) star1.visibility = View.VISIBLE
        if (value >= 1.5F) starOneHalf.visibility = View.VISIBLE
        if (value >= 2F) star2.visibility = View.VISIBLE
        if (value >= 2.5F) starTwoHalf.visibility = View.VISIBLE
        if (value >= 3F) star3.visibility = View.VISIBLE
        if (value >= 3.5F) starThreeHalf.visibility = View.VISIBLE
        if (value >= 4F) star4.visibility = View.VISIBLE
        if (value >= 4.5F) starFourHalf.visibility = View.VISIBLE
        if (value >= 5F) star5.visibility = View.VISIBLE
    }
}
