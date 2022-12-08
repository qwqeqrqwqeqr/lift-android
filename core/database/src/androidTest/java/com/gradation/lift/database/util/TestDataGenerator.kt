package com.gradation.lift.database.util

import com.gradation.lift.database.model.RoutineSetEntity
import com.gradation.lift.database.model.WorkCategoryEntity
import com.gradation.lift.model.data.Routine
import com.gradation.lift.model.data.Week
import com.gradation.lift.model.data.WorkPart
import com.gradation.lift.model.data.WorkSet

internal object TestDataGenerator {

    private val testWorkSet1 = WorkSet(weight = 60F, repetition = 12)
    private val testWorkSet2 = WorkSet(weight = 60F, repetition = 12)
    private val testWorkSet3 = WorkSet(weight = 80F, repetition = 10)
    private val testWorkSet4 = WorkSet(weight = 80F, repetition = 10)
    private val testWorkSet5 = WorkSet(weight = 100F, repetition = 10)


    private val testRoutine1 = Routine(
        name = "스쿼트", WorkPart.lowerBody, listOf(
            testWorkSet1, testWorkSet2,
            testWorkSet3, testWorkSet4, testWorkSet5
        )
    )

    private val testRoutine2 = Routine(
        name = "데드리프트", WorkPart.lowerBody, listOf(
            testWorkSet1, testWorkSet2,
            testWorkSet3, testWorkSet4, testWorkSet5
        )
    )

    private val testRoutine3 = Routine(
        name = "스쿼트", WorkPart.lowerBody, listOf(
            testWorkSet1, testWorkSet2,
            testWorkSet3, testWorkSet4, testWorkSet5
        )
    )

    private val testWorkPart1 = WorkPart.lowerBody
    private val testWorkPart2 = WorkPart.arm

    val testRoutineSet1 = RoutineSetEntity(
        name = "테스트 루틴 1",
        shortDescription = "테스트용 루틴입니다",
        week = Week.Monday,
        routineList = listOf(testRoutine1, testRoutine2, testRoutine3)
    )

    val testRoutineSet2 = RoutineSetEntity(
        id = 50,
        name = "테스트 루틴 2",
        shortDescription = "테스트용 루틴입니다.",
        week = Week.Tuesday,
        routineList = listOf(testRoutine1, testRoutine2, testRoutine3)
    )

    val testWorkCategory1 = WorkCategoryEntity(
        name = "스쿼트",
        workPart = testWorkPart1,
        shortDescription = "테스트용 카테고리",
        longDescription = "테스트용 카테고리입니다.",
        customFlag = false
    )

    val testWorkCategory2 = WorkCategoryEntity(
        id = 30,
        name = "바벨컬",
        workPart = testWorkPart2,
        shortDescription = "테스트용 카테고리",
        longDescription = "테스트용 카테고리입니다.",
        customFlag = true
    )


}