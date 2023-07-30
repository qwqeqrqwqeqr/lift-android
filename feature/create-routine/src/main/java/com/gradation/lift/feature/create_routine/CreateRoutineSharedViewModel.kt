package com.gradation.lift.feature.create_routine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.CreateRoutineSetUseCase
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.routine.CreateRoutine
import com.gradation.lift.model.routine.CreateRoutineSetRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import javax.inject.Inject


@HiltViewModel
class CreateRoutineSharedViewModel @Inject constructor(
    private val createRoutineSetUseCase: CreateRoutineSetUseCase,
) : ViewModel() {


    private val _name = MutableStateFlow("")
    var name = _name.asStateFlow()

    private val _description = MutableStateFlow("")
    var description = _description.asStateFlow()

    private val _picture = MutableStateFlow("")
    var picture = _picture.asStateFlow()

    private val _weekday = MutableStateFlow(emptyList<Weekday>())
    var weekday = _weekday.asStateFlow()

    private val _routine = MutableStateFlow(emptyList<CreateRoutine>())
    var routine = _routine.asStateFlow()


    val nameCondition = name.map { it.isNotEmpty() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )
    val descriptionCondition = description.map { it.isNotEmpty() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    private val _onVisibleCancelDialog = MutableStateFlow(false)
    var onVisibleCancelDialog = _onVisibleCancelDialog.asStateFlow()


    val navigationCondition = combine(
        nameCondition,
        descriptionCondition,
        picture,
        weekday,
        routine
    ) {
            nameCondition,
            descriptionCondition,
            picture,
            weekday,
            routine,
        ->
        nameCondition &&
                descriptionCondition &&
                picture.isNotEmpty() &&
                weekday.isNotEmpty() &&
                routine.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )


    fun updateName(): (String) -> Unit = {
        _name.update { it }
    }

    fun updateDescription(): (String) -> Unit = {
        _description.update { it }
    }

    fun updatePicture(): (String) -> Unit = {
        _picture.update { it }
    }

    fun updateWeekday(): (Weekday) -> Unit = { weekday ->
        if (weekday in _weekday.value) {
            _weekday.update { it.minusElement(weekday) }
        } else {
            _weekday.update { it.plusElement(weekday) }
        }
    }


    fun addRoutineSet(): (CreateRoutine) -> Unit = { routine ->
        _routine.update { it.plusElement(routine) }
    }

    fun removeRoutineSet(): (CreateRoutine) -> Unit = { routine ->
        _routine.update { it.minusElement(routine) }
    }


    fun createRoutine() {
        viewModelScope.launch {
            createRoutineSetUseCase(
                CreateRoutineSetRoutine(
                    name.value,
                    description.value,
                    weekday.value,
                    picture.value,
                    routine.value
                )
            ).collect {
                when (it) {
                    is DataState.Success -> _onVisibleCancelDialog.value = it.data
                    is DataState.Fail -> _onVisibleCancelDialog.value = false
                }
            }
        }
    }

}



