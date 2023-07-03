package com.gradation.lift.navigation.saved_state

import androidx.navigation.NavController


fun NavController.setStringValue(key: String, value: String) {
    this.currentBackStackEntry?.let {
        with(it.savedStateHandle) {
            set(key, value)
        }
    }
}

fun NavController.getStringValue(key: String): String {
    return this.previousBackStackEntry?.savedStateHandle?.get<String>(key) ?: ""
}

fun NavController.setFloatValue(key: String, value: Float) {
    this.currentBackStackEntry?.let {
        with(it.savedStateHandle) {
            set(key, value)
        }
    }
}

fun NavController.getFloatValue(key: String): Float {
    return this.previousBackStackEntry?.savedStateHandle?.get<Float>(key) ?: 0f
}

fun NavController.setIntValue(key: String, value: Int) {
    this.currentBackStackEntry?.let {
        with(it.savedStateHandle) {
            set(key, value)
        }
    }
}

fun NavController.getIntValue(key: String): Int {
    return this.previousBackStackEntry?.savedStateHandle?.get<Int>(key) ?: 0
}




