package com.gradation.lift.database.util

import com.gradation.lift.database.model.RoutineSetEntity
import com.gradation.lift.database.model.work_category.WorkCategoryEntity
import com.gradation.lift.domain.model.Routine
import com.gradation.lift.domain.model.Week
import com.gradation.lift.domain.model.WorkPart
import com.gradation.lift.domain.model.WorkSet

internal object TestDataGenerator {

    private val testWorkSet1 =
        com.gradation.lift.domain.model.WorkSet(weight = 60.0, repetition = 12)
    private val testWorkSet2 =
        com.gradation.lift.domain.model.WorkSet(weight = 60.0, repetition = 12)
    private val testWorkSet3 =
        com.gradation.lift.domain.model.WorkSet(weight = 80.0, repetition = 10)
    private val testWorkSet4 =
        com.gradation.lift.domain.model.WorkSet(weight = 80.0, repetition = 10)
    private val testWorkSet5 =
        com.gradation.lift.domain.model.WorkSet(weight = 100.0, repetition = 10)


    private val testRoutine1 = com.gradation.lift.domain.model.Routine(
        name = "스쿼트", listOf(
            testWorkSet1, testWorkSet2,
            testWorkSet3, testWorkSet4, testWorkSet5
        )
    )

    private val testRoutine2 = com.gradation.lift.domain.model.Routine(
        name = "데드리프트", listOf(
            testWorkSet1, testWorkSet2,
            testWorkSet3, testWorkSet4, testWorkSet5
        )
    )

    private val testRoutine3 = com.gradation.lift.domain.model.Routine(
        name = "스쿼트", listOf(
            testWorkSet1, testWorkSet2,
            testWorkSet3, testWorkSet4, testWorkSet5
        )
    )

    private val testWorkPart1 = com.gradation.lift.domain.model.WorkPart.lowerBody
    private val testWorkPart2 = com.gradation.lift.domain.model.WorkPart.arm

    val testRoutineSet1 = RoutineSetEntity(
        name = "테스트 루틴 1",
        introduce = "테스트용 루틴입니다",
        week = com.gradation.lift.domain.model.Week.Monday,
        routineList = listOf(testRoutine1, testRoutine2, testRoutine3)
    )

    val testRoutineSet2 = RoutineSetEntity(
        id = 50,
        name = "테스트 루틴 2",
        introduce = "테스트용 루틴입니다.",
        week = com.gradation.lift.domain.model.Week.Tuesday,
        routineList = listOf(testRoutine1, testRoutine2, testRoutine3)
    )

    val testWorkCategory1 = WorkCategoryEntity(
        name = "스쿼트",
        workPart = testWorkPart1,
        introduce = "테스트용 카테고리",
        description = "테스트용 카테고리입니다.",
        customFlag = false
    )

    val testWorkCategory2 = WorkCategoryEntity(
        id = 30,
        name = "바벨컬",
        workPart = testWorkPart2,
        introduce = "테스트용 카테고리",
        description = "테스트용 카테고리입니다.",
        customFlag = true
    )


}