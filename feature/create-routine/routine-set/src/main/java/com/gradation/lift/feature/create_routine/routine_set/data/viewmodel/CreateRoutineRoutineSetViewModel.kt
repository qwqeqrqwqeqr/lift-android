package com.gradation.lift.feature.create_routine.routine_set.data.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * [CreateRoutineRoutineSetViewModel]
 * @property onVisibleCancelDialog 취소 팝업의 상태 (true 일 때 ,팝업이 열린다)
 * @since  2023-08-20 14:02:25
 */
class CreateRoutineRoutineSetViewModel @Inject constructor(

) : ViewModel() {


    val onVisibleCancelDialog = MutableStateFlow(false)

    fun visibleCancelDialog(): () -> Unit = { onVisibleCancelDialog.value = true }
    fun invisibleCancelDialog(): () -> Unit = { onVisibleCancelDialog.value = false }

}

