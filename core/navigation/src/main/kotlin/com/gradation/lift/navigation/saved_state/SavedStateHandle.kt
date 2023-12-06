package com.gradation.lift.navigation.saved_state

import androidx.navigation.NavController


fun <T> NavController.setValueSavedStateHandle(key: String, value: T) {
    this.currentBackStackEntry?.let {
        with(it.savedStateHandle) {
            set(key, value)
        }
    }
}

fun <T> NavController.getValueSavedStateHandle(key: String): T? {
    return this.currentBackStack.value.mapNotNull {
        it.savedStateHandle.get<T>(key)
    }.let { if (it.isNotEmpty()) it.first() else null }
}







