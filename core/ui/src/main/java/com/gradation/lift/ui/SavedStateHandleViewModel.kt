package com.gradation.lift.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class SavedStateHandleViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) :ViewModel()  {
    fun setStringValue(key: String, value: String) {
        with(savedStateHandle) {
            set(key, value)
        }
    }

    fun getStringValue(key:String): String {
        return savedStateHandle.get<String>(key) ?: "오류"
    }

}

