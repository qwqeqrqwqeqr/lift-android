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



fun  NavController.findValueInBackStackEntry(keyList: List<String>): Map<String, String> {
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







