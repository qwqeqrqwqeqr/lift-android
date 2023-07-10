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


fun NavController.findValueInBackStackEntry(keyList: List<String>): Map<String, String> {
    val valueList = HashMap<String, String>()
    backQueue.reversed().forEach { backStackEntry ->
        keyList.forEach { key ->
            backStackEntry.savedStateHandle.get<String>(key)?.let { value ->
                valueList[key] = value
            }
        }
    }
    return valueList
}







