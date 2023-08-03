package com.gradation.lift.database.data

import com.gradation.lift.database.data.TestEntityDataGenerator.WorkCategory.workCategoryEntity1
import com.gradation.lift.database.data.TestEntityDataGenerator.WorkCategory.workCategoryEntity2
import com.gradation.lift.database.entity.history.HistoryEntity
import com.gradation.lift.database.entity.history.HistoryRoutineEntity
import com.gradation.lift.database.entity.picture.RoutineSetPictureEntity
import com.gradation.lift.database.entity.picture.UserProfilePictureEntity
import com.gradation.lift.database.entity.routine.RoutineEntity
import com.gradation.lift.database.entity.routine.RoutineSetRoutineEntity
import com.gradation.lift.database.entity.user.UserEntity
import com.gradation.lift.database.entity.work.WorkEntity
import com.gradation.lift.database.entity.work.WorkRoutineEntity
import com.gradation.lift.database.entity.work_category.WorkCategoryEntity
import com.gradation.lift.database.entity.work_category.WorkPartEntity
import com.gradation.lift.model.BuildConfig
import com.gradation.lift.model.common.UnitOfWeight
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_URL_DATA
import com.gradation.lift.model.work.WorkSet
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

object TestEntityDataGenerator {
    const val TEST_DATABASE = "test_database"


    object WorkPart {
        internal val workPartEntity1 = WorkPartEntity(id = 1, name = "어깨")
        internal val workPartEntity2 = WorkPartEntity(id = 2, name = "등")


        val workPartEntityList = listOf(
            workPartEntity1,
            workPartEntity2
        )
    }

    object History{
        internal val historyEntity1 = HistoryEntity(
            id = 1,
            comment = "보람찬 하루",
            score = 5,
            restTime = LocalTime(0, 10, 0),
            totalTime = LocalTime(0, 30, 0),
            historyTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
        )
        internal val historyEntity2 = HistoryEntity(
            id = 2,
            comment = "행복한 하루",
            score = 2,
            restTime = LocalTime(0, 10, 0),
            totalTime = LocalTime(0, 30, 0),
            historyTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
        )
        internal val historyEntityList = listOf(historyEntity1, historyEntity2)

        internal val historyRoutineEntity1 = HistoryRoutineEntity(
            id = 1,
            historyId = 1,
            workCategory = workCategoryEntity1,
            workSetList = listOf(
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12)
            )
        )

        internal val historyRoutineEntity2 = HistoryRoutineEntity(
            id = 2,
            historyId = 2,
            workCategory= workCategoryEntity2,
            workSetList = listOf(
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12)
            )
        )

        internal val historyRoutineEntityList = listOf(historyRoutineEntity1, historyRoutineEntity2)

    }

    object Picture{
        val userProfilePictureEntity1 = UserProfilePictureEntity(
            id = 1,
            url = BuildConfig.LIFT_S3_URL+FAKE_URL_DATA
        )
        val userProfilePictureEntity2 = UserProfilePictureEntity(
            id = 2,
            url = BuildConfig.LIFT_S3_URL+FAKE_URL_DATA
        )

        val routineSetPictureEntity1 = RoutineSetPictureEntity(
            id = 1,
            category = FAKE_STRING_DATA,
            url = BuildConfig.LIFT_S3_URL+FAKE_URL_DATA
        )
        val routineSetPictureEntity2 = RoutineSetPictureEntity(
            id = 2,
            category = FAKE_STRING_DATA,
            url = BuildConfig.LIFT_S3_URL+FAKE_URL_DATA
        )

        val userProfilePictureEntityList = listOf(
            userProfilePictureEntity1,
            userProfilePictureEntity2
        )
        val routineSetPictureEntityList = listOf(
            routineSetPictureEntity1,
            routineSetPictureEntity2
        )
    }

    object Work{
        internal val workEntity = WorkEntity(
            id = 1,
            restTime = LocalTime(0, 10, 0),
            totalTime = LocalTime(0, 30, 0),
        )
        internal val workRoutineEntity = WorkRoutineEntity(
            id = 1,
            workId = 1,
            workCategory = workCategoryEntity1,
            workSetList = listOf(
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12)
            )
        )

        internal val workRoutineEntityList = listOf(workRoutineEntity)
    }

    object User {
        internal val userEntity = UserEntity(
            name = FAKE_STRING_DATA,
            gender = Gender.Male(),
            height = 180.0f,
            weight = 83.3f,
            profilePicture = BuildConfig.LIFT_S3_URL+FAKE_URL_DATA,
            unitOfWeight = UnitOfWeight.Kg()
        )

    }

    object WorkCategory {
        internal val workCategoryEntity1 = WorkCategoryEntity(
            id = 1,
            name = "숄더프레스",
            workPart = WorkPart.workPartEntity1,
            introduce = "Lorem ipsum dolor sit amet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

        )
        internal val workCategoryEntity2 = WorkCategoryEntity(
            id = 2,
            name = "데드리프트",
            workPart = WorkPart.workPartEntity2,
            introduce = "Lorem ipsum dolor sit amet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        )

        val workCategoryEntityList = listOf(
            workCategoryEntity1,
            workCategoryEntity2
        )
    }

    object Routine {
         val routineEntity1 = RoutineEntity(
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
         val routineEntity2 = RoutineEntity(
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
         val routineSetRoutineEntity1 = RoutineSetRoutineEntity(
            id = 1,
            name = "행복한 월요일 루틴",
            description = "가볍게 하는 운동",
            weekday = Weekday.Monday(),
            picture = "http://",
        )

         val routineSetRoutineEntity2 = RoutineSetRoutineEntity(
            id = 2,
            name = "등 단련 루틴",
            description = "집중 등 운동",
            weekday = Weekday.Monday(),
            picture = "http://",
        )

        val routineSetRoutineEntityList = listOf(
            routineSetRoutineEntity1,
            routineSetRoutineEntity2
        )
    }
}