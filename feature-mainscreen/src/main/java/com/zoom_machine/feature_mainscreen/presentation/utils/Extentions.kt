package com.zoom_machine.feature_mainscreen.presentation.utils

import com.zoom_machine.feature_mainscreen.R


const val PHONES = 0
const val VIEW_ALL ="view_all"
const val SELECT_CATEGORY = "Select Category"
const val SEE_MORE = "see more"
const val BEST_SELLER = "Best Seller"

fun getListResources() = listOf<Pair<Int, Int>>(
    Pair(R.drawable.ic_phone, R.string.phones),
    Pair(R.drawable.ic_computer, R.string.computer),
    Pair(R.drawable.ic_health, R.string.health),
    Pair(R.drawable.ic_book, R.string.books),
    Pair(R.drawable.ic_phone, R.string.phones),
    Pair(R.drawable.ic_book, R.string.books)
)
