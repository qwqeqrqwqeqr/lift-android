package com.gradation.lift.feature.create_routine.routine_set.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetWeekDateUseCase
import com.gradation.lift.domain.usecase.routine.CreateRoutineSetUseCase
import com.gradation.lift.model.model.common.Weekday
import com.gradation.lift.model.model.common.toWeekday
import com.gradation.lift.model.model.routine.CreateRoutine
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateRoutineSharedViewModel @Inject constructor(
    private val createRoutineSetUseCase: CreateRoutineSetUseCase,
    private val getWeekDateUseCase: GetWeekDateUseCase,
) : ViewModel() {


    private val weekday = MutableStateFlow(emptyList<Weekday>())
    val name = MutableStateFlow("")
    val description = MutableStateFlow("")
    val picture = MutableStateFlow("")
    val routine = MutableStateFlow(emptyList<CreateRoutine>())

    val tempWorkCategory = MutableStateFlow("")

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

    @RequiresApi(Build.VERSION_CODES.O)
    internal val weekdayCardList = weekday.map { weekdayList ->
        getWeekDateUseCase().map { localDate ->
            WeekdayCard(
                weekday = localDate.toWeekday(),
                selected = localDate.toWeekday() in weekdayList
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )


    val createRoutineCondition = combine(
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

    val createRoutineUiState = MutableStateFlow(CreateRoutineUiState.Loading)


    fun updateName(): (String) -> Unit = {
        name.value = it
    }

    fun updateDescription(): (String) -> Unit = {
        description.value = it
    }

    fun updatePicture(value: String) {
        picture.value = value
    }

    fun updateWeekday(): (Weekday) -> Unit = { value ->
        if (value in weekday.value) {
            weekday.update { it.minusElement(value) }
        } else {
            weekday.update { it.plusElement(value) }
        }
    }

    fun updateTempWorkCategory(workCategory: String) {
        tempWorkCategory.value = workCategory
    }


    fun addRoutineSet(): (CreateRoutine) -> Unit = { createRoutine ->
        routine.update { it.plusElement(createRoutine) }
    }

    fun removeRoutineSet(): (CreateRoutine) -> Unit = { createRoutine ->
        routine.update { it.minusElement(createRoutine) }
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
                    is DataState.Success -> if (it.data) CreateRoutineUiState.Success else CreateRoutineUiState.Fail
                    is DataState.Fail -> {
                        CreateRoutineUiState.Fail
                    }
                }
            }
        }
    }
}

internal data class WeekdayCard(
    val weekday: Weekday = Weekday.None(),
    var selected: Boolean = false,
)

sealed interface CreateRoutineUiState {
    object Success : CreateRoutineUiState
    object Loading : CreateRoutineUiState
    object Fail : CreateRoutineUiState
}


