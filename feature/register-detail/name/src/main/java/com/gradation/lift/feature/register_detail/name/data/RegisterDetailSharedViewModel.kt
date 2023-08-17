package com.gradation.lift.feature.register_detail.name.data

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterDetailSharedViewModel @Inject constructor(
) : ViewModel() {

    val currentRegisterProgressNumber: MutableStateFlow<Int> = MutableStateFlow(1)
    val totalRegisterProgressNumber: MutableStateFlow<Int> = MutableStateFlow(4)


    fun updateCurrentRegisterProgressNumber(): (Int) -> Unit = {
        currentRegisterProgressNumber.value = it
    }
}
