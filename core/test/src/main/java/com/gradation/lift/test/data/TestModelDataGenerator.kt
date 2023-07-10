package com.gradation.lift.test.data

import com.gradation.lift.domain.model.common.*
import com.gradation.lift.model.routine.*
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.model.work.WorkSet
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_STRING_DATA


object TestModelDataGenerator {


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
    val routineSetModel1 = RoutineSet(
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

    private val routineSetRoutineModel1 = RoutineSetRoutine(
        id = 1,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        repeatIntervalType = RepeatIntervalType.DayType(interval = 3),
        routine = listOf(routineModel1)
    )

    private val routineSetRoutineModel2 = RoutineSetRoutine(
        id = 2,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        repeatIntervalType = RepeatIntervalType.WeekDayType(weekday = WeekDay.Monday),
        routine = listOf(routineModel2)
    )



    val workPartModelList = listOf(workPartModel1, workPartModel2)
    val workCategoryModelList = listOf(workCategoryModel1, workCategoryModel2)

    val routineModelList = listOf(routineModel1, routineModel2)
    val routineSetModelList = listOf(routineSetModel1, routineSetModel2)
    val routineSetRoutineModelList = listOf(routineSetRoutineModel1, routineSetRoutineModel2)


    val FAKE_CREATE_ROUTINE_SET = CreateRoutineSetRoutine(
        userId = FAKE_STRING_DATA,
        shortDescription = FAKE_STRING_DATA,
        longDescription = FAKE_STRING_DATA,
        repeatIntervalType = RepeatIntervalType.DayType(interval = FAKE_INT_DATA),
        routine = emptyList(),
    )


}

