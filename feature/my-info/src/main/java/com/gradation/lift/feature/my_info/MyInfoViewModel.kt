package com.gradation.lift.feature.my_info

import androidx.lifecycle.ViewModel
import com.gradation.lift.common.di.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyInfoViewModel  @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
):ViewModel() {
}