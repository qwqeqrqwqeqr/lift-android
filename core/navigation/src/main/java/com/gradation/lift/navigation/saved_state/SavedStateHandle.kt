package com.gradation.lift.navigation.saved_state

import androidx.navigation.NavController



fun <T> NavController.setValueSavedStateHandle(key: String, value:T){
    this.currentBackStackEntry?.let {
        with(it.savedStateHandle) {
            set(key, value)
        }
    }
}
fun <T> NavController.getValueSavedStateHandle(key: String): T? {
    return this.previousBackStackEntry?.savedStateHandle?.get<T>(key)
}







