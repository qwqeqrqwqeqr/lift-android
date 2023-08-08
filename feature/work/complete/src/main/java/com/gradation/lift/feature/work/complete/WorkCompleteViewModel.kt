package com.gradation.lift.feature.work.complete

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class WorkCompleteViewModel @Inject constructor(
) : ViewModel() {


    val score = MutableStateFlow(5)


    fun updateScore(): (Int) -> Unit = {
        score.value = it
    }

}