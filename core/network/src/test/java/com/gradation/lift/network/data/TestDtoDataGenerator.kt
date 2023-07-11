package com.gradation.lift.network.data

import com.gradation.lift.model.routine.Weekday.Companion.MONDAY
import com.gradation.lift.network.dto.routine.*
import com.gradation.lift.network.dto.work.*


internal object TestDtoDataGenerator {

    private val workPartDto1 = WorkPartDto(id = 1, name = "어꺠")
    private val workPartDto2 = WorkPartDto(id = 2, name = "등")
    private val workCategoryDto1 = WorkCategoryDto(
        id = 1,
        name = "숄더프레스",
        workpart = workPartDto1,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

    )
    private val workCategoryDto2 = WorkCategoryDto(
        id = 2,
        name = "데드리프트",
        workpart = workPartDto2,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )


    private val routineDto1 = RoutineDto(
        routineId = 1,
        routineSetId = 1,
        workCategory = workCategoryDto1,
        workWeightList = listOf(10f, 10f, 10f, 10f, 10f),
        workRepetitionList = listOf(12, 12, 12, 12, 12),
        maxWeight = 10f,
        minWeight = 10f,
        totalWeight = 50f,
        maxRepetition = 12,
        minRepetition = 12,
        totalRepetition = 12
    )
    private val routineDto2 = RoutineDto(
        routineId = 2,
        routineSetId = 2,
        workCategory = workCategoryDto2,
        workWeightList = listOf(10f, 10f, 10f, 10f, 10f),
        workRepetitionList = listOf(12, 12, 12, 12, 12),
        maxWeight = 10f,
        minWeight = 10f,
        totalWeight = 50f,
        maxRepetition = 12,
        minRepetition = 12,
        totalRepetition = 12
    )

    private val routineSetDto1 = RoutineSetDto(
        routineSetId = 1,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        weekday = MONDAY
    )
    private val routineSetDto2 = RoutineSetDto(
        routineSetId = 2,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        weekday = MONDAY
    )

    private val routineSetRoutineDto1 = RoutineSetRoutineDto(
        routineDto = routineDto1,
        routineSetDto = routineSetDto1
    )

    private val routineSetRoutineDto2 = RoutineSetRoutineDto(
        routineDto = routineDto2,
        routineSetDto = routineSetDto2
    )


    val getWorkPartDto = GetWorkPartResponseDto(workpart = listOf(workPartDto1, workPartDto2))
    val getWorkCategoryDto =
        GetWorkCategoryResponseDto(workCategory = listOf(workCategoryDto1, workCategoryDto2))
    val getWorkCategoryByWorkPartDto =
        GetWorkCategoryByWorkPartResponseDto(
            workCategory = listOf(
                workCategoryDto1,
                workCategoryDto2
            )
        )


    val getRoutineDto = GetRoutineResponseDto(
        routine = listOf(
            routineDto1,
            routineDto2
        )
    )


    val getRoutineSetRoutineResponseDto = GetRoutineSetRoutineResponseDto(
        routineSetRoutine = listOf(
            routineSetRoutineDto1,
            routineSetRoutineDto2,
        )
    )

    val getRoutineSetRoutineByRoutineSetIdResponseDto =
        GetRoutineSetRoutineByRoutineSetIdResponseDto(
            routineSetRoutine = listOf(
                routineSetRoutineDto1,
                routineSetRoutineDto2
            )
        )

    val getRoutineSetRoutineByWeekdayResponseDto = GetRoutineSetRoutineByWeekdayResponseDto(
        routineSetRoutine = listOf(
            routineSetRoutineDto1,
            routineSetRoutineDto2,
        )
    )


}

