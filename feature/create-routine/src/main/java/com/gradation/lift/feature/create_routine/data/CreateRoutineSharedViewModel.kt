package com.gradation.lift.feature.create_routine.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetWeekDateUseCase
import com.gradation.lift.domain.usecase.routine.CreateRoutineSetUseCase
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.common.toWeekday
import com.gradation.lift.model.routine.CreateRoutine
import com.gradation.lift.model.routine.CreateRoutineSetRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    val navigationCondition = MutableStateFlow(false)


    fun updateName(): (String) -> Unit = {
        name.value = it
    }

    fun updateDescription(): (String) -> Unit = {
        description.value = it
    }

    fun updatePicture(value:String) {
        picture.value = value
    }

    fun updateWeekday(): (Weekday) -> Unit = { value ->
        if (value in weekday.value) {
            weekday.update { it.minusElement(value) }
        } else {
            weekday.update { it.plusElement(value) }
        }
    }


    fun addRoutineSet(): (CreateRoutine) -> Unit = { value ->
        routine.update { it.plusElement(value) }
    }

    fun removeRoutineSet(): (CreateRoutine) -> Unit = { value ->
        routine.update { it.minusElement(value) }
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
                    is DataState.Success -> navigationCondition.value = it.data
                    is DataState.Fail -> navigationCondition.value = false
                }
            }
        }
    }
}

internal data class WeekdayCard(
    val weekday: Weekday = Weekday.None(),
    var selected: Boolean = false,
)



