package com.gradation.lift.database.util

import com.gradation.lift.database.model.RoutineSetEntity
import com.gradation.lift.model.data.Routine
import com.gradation.lift.model.data.Week
import com.gradation.lift.model.data.WorkPart
import com.gradation.lift.model.data.WorkSet

internal object TestDataGenerator {

    private val testWorkSetOne = WorkSet(weight = 60F, repetition = 12)
    private val testWorkSetTwo = WorkSet(weight = 60F, repetition = 12)
    private val testWorkSetThree = WorkSet(weight = 80F, repetition = 10)
    private val testWorkSetFour = WorkSet(weight = 80F, repetition = 10)
    private val testWorkSetFive = WorkSet(weight = 100F, repetition = 10)


    private val testRoutineOne = Routine(
        name = "스쿼트", WorkPart.lowerBody, listOf(
            testWorkSetOne, testWorkSetTwo,
            testWorkSetThree, testWorkSetFour, testWorkSetFive
        )
    )

    private val testRoutineTwo = Routine(
        name = "데드리프트", WorkPart.lowerBody, listOf(
            testWorkSetOne, testWorkSetTwo,
            testWorkSetThree, testWorkSetFour, testWorkSetFive
        )
    )

    private val testRoutineThree = Routine(
        name = "스쿼트", WorkPart.lowerBody, listOf(
            testWorkSetOne, testWorkSetTwo,
            testWorkSetThree, testWorkSetFour, testWorkSetFive
        )
    )

    val testRoutineSetOne = RoutineSetEntity(
        name = "테스트 루틴 1",
        shortDescription = "테스트용 루틴입니다",
        week = Week.Monday,
        routineList = listOf(testRoutineOne, testRoutineTwo, testRoutineThree)
    )

    val testRoutineSetTwo = RoutineSetEntity(
        id = 50,
        name = "테스트 루틴 2",
        shortDescription = "테스트용 루틴입니다.",
        week = Week.Tuesday,
        routineList = listOf(testRoutineOne, testRoutineTwo, testRoutineThree)
    )

    const val updatedTestRoutineSetName = "테스트 루틴 3"
}