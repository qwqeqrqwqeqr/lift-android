package com.gradation.lift.database.data

import com.gradation.lift.database.data.TestEntityDataGenerator.WorkCategory.workCategoryEntity1
import com.gradation.lift.database.data.TestEntityDataGenerator.WorkCategory.workCategoryEntity2
import com.gradation.lift.database.model.routine.RoutineEntity
import com.gradation.lift.database.model.routine.RoutineSetRoutineEntity
import com.gradation.lift.database.model.user.UserEntity
import com.gradation.lift.database.model.work_category.WorkCategoryEntity
import com.gradation.lift.database.model.work_category.WorkPartEntity
import com.gradation.lift.model.BuildConfig
import com.gradation.lift.model.common.UnitOfWeight
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_URL_DATA
import com.gradation.lift.model.work.WorkSet

object TestEntityDataGenerator {
    const val TEST_DATABASE = "test_database"


    object WorkPart {
        internal val workpartEntity1 = WorkPartEntity(id = 1, name = "어깨")
        internal val workpartEntity2 = WorkPartEntity(id = 2, name = "등")


        val workPartEntityList = listOf(
            workpartEntity1,
            workpartEntity2
        )

    }


    object User {
        internal val userEntity = UserEntity(
            name = FAKE_STRING_DATA,
            gender = Gender.Male().value,
            height = 180.0f,
            weight = 83.3f,
            profilePicture = BuildConfig.LIFT_S3_URL+FAKE_URL_DATA,
            unitOfWeight = UnitOfWeight.Kg().value
        )

    }

    object WorkCategory {
        internal val workCategoryEntity1 = WorkCategoryEntity(
            id = 1,
            name = "숄더프레스",
            workPart = WorkPart.workpartEntity1,
            introduce = "Lorem ipsum dolor sit amet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

        )
        internal val workCategoryEntity2 = WorkCategoryEntity(
            id = 2,
            name = "데드리프트",
            workPart = WorkPart.workpartEntity2,
            introduce = "Lorem ipsum dolor sit amet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        )

        val workCategoryEntityList = listOf(
            workCategoryEntity1,
            workCategoryEntity2
        )
    }

    object Routine {
        private val routineEntity1 = RoutineEntity(
            id = 1,
            routineSetId = 1,
            workCategoryEntity = workCategoryEntity1,
            workSetList = listOf(
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12)
            )
        )
        private val routineEntity2 = RoutineEntity(
            id = 2,
            routineSetId = 2,
            workCategoryEntity = workCategoryEntity2,
            workSetList = listOf(
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12)
            )
        )

        val routineEntityList = listOf(
            routineEntity1,
            routineEntity2
        )
    }

    object RoutineSetRoutine {
        private val routineSetRoutineEntity1 = RoutineSetRoutineEntity(
            id = 1,
            name = "행복한 월요일 루틴",
            description = "가볍게 하는 운동",
            weekday = Weekday.Monday(),
            picture = null,
        )

        private val routineSetRoutineEntity2 = RoutineSetRoutineEntity(
            id = 2,
            name = "등 단련 루틴",
            description = "집중 등 운동",
            weekday = Weekday.Monday(),
            picture = null,
        )

        val routineSetRoutineEntityList = listOf(
            routineSetRoutineEntity1,
            routineSetRoutineEntity2
        )
    }
}