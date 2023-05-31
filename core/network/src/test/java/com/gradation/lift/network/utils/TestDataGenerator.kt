package com.gradation.lift.network.utils

import com.gradation.lift.domain.model.common.*
import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.routine.RoutineSet
import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkPart
import com.gradation.lift.domain.model.work.WorkSet
import com.gradation.lift.network.dto.routine.*
import com.gradation.lift.network.dto.work.*


internal object TestDataGenerator {

    private val workPartModel1 = WorkPart(id = 1, name = "어꺠")
    private val workPartModel2 = WorkPart(id = 2, name = "등")
    private val workCategoryModel1 = WorkCategory(
        id = 1,
        name = "숄더프레스",
        workpart = workPartModel1,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )
    private val workCategoryModel2 = WorkCategory(
        id = 2,
        name = "데드리프트",
        workpart = workPartModel2,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )
    private val routineModel1 = Routine(
        id = 1,
        routineSetId = 1,
        workCategory = workCategoryModel1,
        workSetList = listOf(
            WorkSet(weight = 10f, repetition = 12),
            WorkSet(weight = 10f, repetition = 12),
            WorkSet(weight = 10f, repetition = 12),
            WorkSet(weight = 10f, repetition = 12),
            WorkSet(weight = 10f, repetition = 12)
        ),
        maxWeight = 10f,
        minWeight = 10f,
        totalWeight = 50f,
        maxRepetition = 12,
        minRepetition = 12,
        totalRepetition = 12
    )
    private val routineModel2 = Routine(
        id = 2,
        routineSetId = 2,
        workCategory = workCategoryModel2,
        workSetList = listOf(
            WorkSet(weight = 10f, repetition = 12),
            WorkSet(weight = 10f, repetition = 12),
            WorkSet(weight = 10f, repetition = 12),
            WorkSet(weight = 10f, repetition = 12),
            WorkSet(weight = 10f, repetition = 12)
        ),
        maxWeight = 10f,
        minWeight = 10f,
        totalWeight = 50f,
        maxRepetition = 12,
        minRepetition = 12,
        totalRepetition = 12
    )
    private val routineSetModel1 = RoutineSet(
        id = 1,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        repeatIntervalType = RepeatIntervalType.DayType(interval = 3)
    )
    private val routineSetModel2 = RoutineSet(
        id = 2,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        repeatIntervalType = RepeatIntervalType.WeekDayType(weekday = WeekDay.Monday)
    )


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
        id = 1,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        repeatType = WEEK_DAY_TYPE,
        repeatInterval = MONDAY
    )


    val workPartModelList = listOf(workPartModel1, workPartModel2)
    val workCategoryModelList = listOf(workCategoryModel1, workCategoryModel2)

    val routineModelList = listOf(routineModel1, routineModel2)
    val routineSetModelList = listOf(routineSetModel1, routineSetModel2)


    val getWorkPartDto = GetWorkPartResponseDto(workpart = listOf(workPartDto1, workPartDto2))
    val getWorkCategoryDto =
        GetWorkCategoryResponseDto(workCategory = listOf(workCategoryDto1, workCategoryDto2))
    val getWorkCategoryByWorkPartDto =
        GetWorkCategoryByWorkPartResponseDto(workCategory = listOf(workCategoryDto1))


    val getRoutineSetDto = GetRoutineSetResponseDto(
        routineSet = listOf(
            routineSetDto1,
            routineSetDto2
        )
    )

    val getRoutineSetByDate = GetRoutineSetByDateResponseDto(
        routineSet = listOf(
            routineSetDto1,
            routineSetDto2
        )
    )

    val getRoutineSetByRoutineSetId = GetRoutineSetByRoutineSetIdResponseDto(
        routine_set = routineSetDto1
    )

    val getRoutine = GetRoutineResponseDto(
        routine = listOf(
            routineDto1,
            routineDto2
        )
    )

    val getRoutineByDate = GetRoutineByDateResponseDto(
        routine = listOf(
            routineDto1,
            routineDto2
        )
    )

    val getRoutineByRoutineSetId = GetRoutineByRoutineSetIdResponseDto(
        routine = listOf(
            routineDto1,
            routineDto2
        )
    )

    val getRoutineByDateAndRoutineSetId = GetRoutineByDateAndRoutineSetIdResponseDto(
        routine = listOf(
            routineDto1,
            routineDto2
        )
    )

}

