package com.example.testtaskfoodapp.ui.common

import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavController.navigateSendParcelable(
    route: String,
    param: Pair<String, Parcelable>?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    param?.let {
        currentBackStackEntry?.arguments?.putParcelable(param.first, param.second)
    }
    navigate(route, builder)
}

fun NavController.navigateSendListParcelable(
    route: String,
    params: List<Pair<String, Parcelable>>?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    params?.let {
        val arguments = currentBackStackEntry?.arguments
        params.forEach { arguments?.putParcelable(it.first, it.second) }
    }

    navigate(route, builder)
}

fun <T> NavController.navigateSendData(
    route: String,
    key: String,
    value: T?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    currentBackStackEntry?.savedStateHandle?.set(key, value)
    navigate(route, builder)
}