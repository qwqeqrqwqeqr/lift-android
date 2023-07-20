package com.gradation.lift.test.data

import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.routine.*
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.model.work.WorkSet
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.test.data.TestModelDataGenerator.Work.workCategoryModel1
import com.gradation.lift.test.data.TestModelDataGenerator.Work.workCategoryModel2
import com.gradation.lift.test.data.TestModelDataGenerator.Work.workPartModel1
import com.gradation.lift.test.data.TestModelDataGenerator.Work.workPartModel2


object TestModelDataGenerator {


    object Work{
        val workPartModel1 = WorkPart(id = 1, name = "어깨")
        val workPartModel2 = WorkPart(id = 2, name = "등")
        val workCategoryModel1 = WorkCategory(
            id = 1,
            name = "숄더프레스",
            workpart = workPartModel1,
            introduce = "Lorem ipsum dolor sit amet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        )
         val workCategoryModel2 = WorkCategory(
            id = 2,
            name = "데드리프트",
            workpart = workPartModel2,
            introduce = "Lorem ipsum dolor sit amet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        )
    }
    object Routine{
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
            )
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
            )
        )
        private val routineSetModel1 = RoutineSet(
            id = 1,
            name = "행복한 월요일 루틴",
            description = "가볍게 하는 운동",
            weekday = Weekday.Monday()
        )
        private val routineSetModel2 = RoutineSet(
            id = 2,
            name = "등 단련 루틴",
            description = "집중 등 운동",
            weekday = Weekday.Monday()
        )

        private val routineSetRoutineModel1 = RoutineSetRoutine(
            id = 1,
            name = "행복한 월요일 루틴",
            description = "가볍게 하는 운동",
            weekday = Weekday.Monday(),
            routine = listOf(routineModel1)
        )

        private val routineSetRoutineModel2 = RoutineSetRoutine(
            id = 2,
            name = "등 단련 루틴",
            description = "집중 등 운동",
            weekday = Weekday.Monday(),
            routine = listOf(routineModel2)
        )



        val workPartModelList = listOf(workPartModel1, workPartModel2)
        val workCategoryModelList = listOf(workCategoryModel1, workCategoryModel2)

        val routineModelList = listOf(routineModel1, routineModel2)
        val routineSetModelList = listOf(routineSetModel1, routineSetModel2)
        val routineSetRoutineModelList = listOf(routineSetRoutineModel1, routineSetRoutineModel2)


        val createRoutineSetRoutineModel = CreateRoutineSetRoutine(
            name = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            weekday = listOf(Weekday.Monday(),Weekday.Tuesday()) ,
            routine = emptyList(),
        )
    }


}

