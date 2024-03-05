package com.gradation.lift.database.data

import com.gradation.lift.database.entity.badge.BadgeEntity
import com.gradation.lift.database.entity.badge.UserBadgeEntity
import com.gradation.lift.database.entity.history.HistoryEntity
import com.gradation.lift.database.entity.history.HistoryRoutineEntity
import com.gradation.lift.database.entity.notice.NoticeEntity
import com.gradation.lift.database.entity.picture.RoutineSetPictureEntity
import com.gradation.lift.database.entity.picture.UserProfilePictureEntity
import com.gradation.lift.database.entity.routine.RoutineEntity
import com.gradation.lift.database.entity.routine.RoutineSetRoutineEntity
import com.gradation.lift.database.entity.user.UserEntity
import com.gradation.lift.database.entity.work.WorkEntity
import com.gradation.lift.database.entity.work.WorkRoutineEntity
import com.gradation.lift.database.entity.workCategory.WorkCategoryEntity
import com.gradation.lift.database.entity.workCategory.WorkPartEntity
import com.gradation.lift.model.BuildConfig
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.UnitOfWeight
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_COLOR_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_DATE_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_LABEL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_DESCRIPTION_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_NAME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_REPETITION_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_WEIGHT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_TIME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_TIME_STAMP_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_URL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WEEKDAY_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WORK_PART_NAME_DATA
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toLocalDateTime
import java.util.UUID

object TestEntityDataGenerator {
    const val TEST_DATABASE = "test_database"


    object Badge {
        internal val badgeEntity = BadgeEntity(
            id = FAKE_INT_DATA,
            name = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            hint = FAKE_STRING_DATA,
            url = FAKE_URL_DATA,
            color = FAKE_COLOR_DATA,
            backgroundColor = FAKE_COLOR_DATA
        )

        internal val userBadgeEntity = UserBadgeEntity(
            badge = badgeEntity,
            badgeTimeStamp = FAKE_TIME_STAMP_DATA.toLocalDateTime(),
            mainFlag = DefaultDataGenerator.FAKE_BOOLEAN_DATA
        )

        val badgeEntityList = listOf(badgeEntity)
        val userBadgeEntityList = listOf(userBadgeEntity)

    }

    object Notification {
        val noticeEntity = NoticeEntity(
            id = FAKE_INT_DATA,
            title = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            category = FAKE_STRING_DATA,
            date = LocalDate.parse(FAKE_DATE_DATA),
        )
        val noticeEntityList = listOf(noticeEntity)
    }

    object WorkPart {
        internal val workPartEntity1 =
            WorkPartEntity(id = FAKE_INT_DATA, name = FAKE_WORK_PART_NAME_DATA)


        val workPartEntityList = listOf(
            workPartEntity1,
        )
    }

    object History {
        internal val historyEntity1 = HistoryEntity(
            id = 1,
            comment = "보람찬 하루",
            score = 5,
            workTime = LocalTime(0, 20, 0),
            restTime = LocalTime(0, 10, 0),
            totalTime = LocalTime(0, 30, 0),
            historyTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
        )
        internal val historyEntity2 = HistoryEntity(
            id = 2,
            comment = "행복한 하루",
            score = 2,
            workTime = LocalTime(0, 20, 0),
            restTime = LocalTime(0, 10, 0),
            totalTime = LocalTime(0, 30, 0),
            historyTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
        )
        internal val historyEntityList = listOf(historyEntity1, historyEntity2)

        internal val historyRoutineEntity1 = HistoryRoutineEntity(
            id = 1,
            historyId = 1,
            workCategoryId = FAKE_INT_DATA,
            workCategoryName = FAKE_STRING_DATA,
            workPart = listOf(FAKE_WORK_PART_NAME_DATA),
            workSetList = listOf(
                WorkSet(
                    weight = FAKE_ROUTINE_WEIGHT_DATA,
                    repetition = FAKE_ROUTINE_REPETITION_DATA
                ),
                WorkSet(
                    weight = FAKE_ROUTINE_WEIGHT_DATA,
                    repetition = FAKE_ROUTINE_REPETITION_DATA
                ),
                WorkSet(
                    weight = FAKE_ROUTINE_WEIGHT_DATA,
                    repetition = FAKE_ROUTINE_REPETITION_DATA
                ),
            )
        )


        internal val historyRoutineEntityList = listOf(historyRoutineEntity1)

    }

    object Picture {
        val userProfilePictureEntity1 = UserProfilePictureEntity(
            id = 1,
            url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA
        )
        val userProfilePictureEntity2 = UserProfilePictureEntity(
            id = 2,
            url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA
        )

        val routineSetPictureEntity1 = RoutineSetPictureEntity(
            id = 1,
            category = FAKE_STRING_DATA,
            url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA
        )
        val routineSetPictureEntity2 = RoutineSetPictureEntity(
            id = 2,
            category = FAKE_STRING_DATA,
            url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA
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

    object Work {
        internal val workEntity = WorkEntity(
            id = UUID.fromString(""),
            workTime = LocalTime.fromSecondOfDay(FAKE_TIME_DATA),
            restTime = LocalTime.fromSecondOfDay(FAKE_TIME_DATA),
            totalTime = LocalTime.fromSecondOfDay(FAKE_TIME_DATA),
            usedRoutineSetIdList = listOf(FAKE_INT_DATA)
        )
        internal val workRoutineEntity = WorkRoutineEntity(
            id = 1,
            workId = UUID.fromString(""),
            workCategoryId = FAKE_INT_DATA,
            workCategoryName = FAKE_STRING_DATA,
            workPart = listOf(FAKE_WORK_PART_NAME_DATA),
            workSetList = listOf(
                WorkSet(
                    weight = FAKE_ROUTINE_WEIGHT_DATA,
                    repetition = FAKE_ROUTINE_REPETITION_DATA
                ),
                WorkSet(
                    weight = FAKE_ROUTINE_WEIGHT_DATA,
                    repetition = FAKE_ROUTINE_REPETITION_DATA
                ),
                WorkSet(
                    weight = FAKE_ROUTINE_WEIGHT_DATA,
                    repetition = FAKE_ROUTINE_REPETITION_DATA
                ),
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
            profilePicture = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
            unitOfWeight = UnitOfWeight.Kg()
        )

    }

    object WorkCategory {
        internal val workCategoryEntity1 = WorkCategoryEntity(
            id = FAKE_INT_DATA,
            name = FAKE_ROUTINE_NAME_DATA,
            equipment = FAKE_STRING_DATA,
            searchTag = listOf(FAKE_STRING_DATA),
            workPart = listOf(FAKE_WORK_PART_NAME_DATA),
            introduce = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            sequence = listOf(FAKE_STRING_DATA),
            effect = listOf(FAKE_STRING_DATA),
            caution = listOf(FAKE_STRING_DATA)
        )

        val workCategoryEntityList = listOf(
            workCategoryEntity1,
        )
    }

    object Routine {
        val routineEntity1 = RoutineEntity(
            id = FAKE_INT_DATA,
            routineSetId = FAKE_INT_DATA,
            workCategoryId = FAKE_INT_DATA,
            workCategoryName = FAKE_STRING_DATA,
            workPart = listOf(FAKE_WORK_PART_NAME_DATA),
            workSetList = listOf(
                WorkSet(
                    weight = FAKE_ROUTINE_WEIGHT_DATA,
                    repetition = FAKE_ROUTINE_REPETITION_DATA
                ),
                WorkSet(
                    weight = FAKE_ROUTINE_WEIGHT_DATA,
                    repetition = FAKE_ROUTINE_REPETITION_DATA
                ),
                WorkSet(
                    weight = FAKE_ROUTINE_WEIGHT_DATA,
                    repetition = FAKE_ROUTINE_REPETITION_DATA
                ),
            )
        )

        val routineEntityList = listOf(
            routineEntity1,
        )
    }

    object RoutineSetRoutine {
        val routineSetRoutineEntity1 = RoutineSetRoutineEntity(
            id = FAKE_INT_DATA,
            name = FAKE_ROUTINE_NAME_DATA,
            description = FAKE_ROUTINE_DESCRIPTION_DATA,
            weekday = listOf(FAKE_WEEKDAY_DATA),
            label = listOf(FAKE_LABEL_DATA),
            count = FAKE_INT_DATA,
            picture = FAKE_URL_DATA,
        )


        val routineSetRoutineEntityList = listOf(
            routineSetRoutineEntity1,
        )
    }
}