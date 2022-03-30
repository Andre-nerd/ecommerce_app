package com.zoom_machine.nanigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import java.io.Serializable

const val NAV_DATA = "navigation data"

fun Fragment.navigate(
    actionId: Int,
    hostId: Int? = null,
    data: Serializable? = null
) {
    val navController = if (hostId == null) {
        findNavController()
    } else {
        Navigation.findNavController(requireActivity(), hostId)
    }
    val bundle = Bundle().apply { putSerializable(NAV_DATA, data) }
    navController.navigate(actionId, bundle)

}

val Fragment.navigationData: Serializable?
    get() = arguments?.getSerializable(NAV_DATA)