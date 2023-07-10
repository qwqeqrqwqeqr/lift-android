package com.gradation.lift.network.data

import com.gradation.lift.domain.model.common.*
import com.gradation.lift.model.routine.DAY_TYPE
import com.gradation.lift.model.routine.MONDAY
import com.gradation.lift.model.routine.WEEK_DAY_TYPE
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
        id = 1,
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
        id = 2,
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
        id = 1,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        repeatType = DAY_TYPE,
        repeatInterval = 3
    )
    private val routineSetDto2 = RoutineSetDto(
        id = 2,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        repeatType = WEEK_DAY_TYPE,
        repeatInterval = MONDAY
    )

    private val routineSetRoutineDto1 = RoutineSetRoutineDto(
        id = 1,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        repeatType = DAY_TYPE,
        repeatInterval = 3,
        routineId = 1,
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

    private val routineSetRoutineDto2 = RoutineSetRoutineDto(
        id = 2,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        repeatType = WEEK_DAY_TYPE,
        repeatInterval = MONDAY,
        routineId = 2,
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


    val getRoutineSetDto = GetRoutineSetResponseDto(
        routineSet = listOf(
            routineSetDto1,
            routineSetDto2
        )
    )


    val getRoutineSetByDateDto = GetRoutineSetByDateResponseDto(
        routineSet = listOf(
            routineSetDto1,
            routineSetDto2
        )
    )

    val getRoutineSetByRoutineSetIdDto = GetRoutineSetByRoutineSetIdResponseDto(
        routine_set = routineSetDto1
    )

    val getRoutineDto = GetRoutineResponseDto(
        routine = listOf(
            routineDto1,
            routineDto2
        )
    )

    val getRoutineByDateDto = GetRoutineByDateResponseDto(
        routine = listOf(
            routineDto1,
            routineDto2
        )
    )

    val getRoutineByRoutineSetIdDto = GetRoutineByRoutineSetIdResponseDto(
        routine = listOf(
            routineDto1,
            routineDto2
        )
    )

    val getRoutineByDateAndRoutineSetIdDto = GetRoutineByDateAndRoutineSetIdResponseDto(
        routine = listOf(
            routineDto1,
            routineDto2
        )
    )


    val getRoutineSetRoutineDto = GetRoutineSetRoutineResponseDto(
        routineSetRoutine = listOf(
            routineSetRoutineDto1,
            routineSetRoutineDto2,
        )
    )

    val getRoutineSetRoutineByDateDto = GetRoutineSetRoutineByWeekdayResponseDto(
        routineSetRoutine = listOf(
            routineSetRoutineDto1,
            routineSetRoutineDto2,
        )
    )

}

