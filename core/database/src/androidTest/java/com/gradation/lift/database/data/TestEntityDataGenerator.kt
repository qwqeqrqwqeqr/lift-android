package com.gradation.lift.database.data

import com.gradation.lift.database.entity.badge.BadgeEntity
import com.gradation.lift.database.entity.history.HistoryEntity
import com.gradation.lift.database.entity.history.HistoryRoutineEntity
import com.gradation.lift.database.entity.routine.RoutineEntity
import com.gradation.lift.database.entity.routine.RoutineSetRoutineEntity
import com.gradation.lift.database.entity.userBadge.UserBadgeEntity
import com.gradation.lift.database.entity.workCategory.WorkCategoryEntity
import com.gradation.lift.database.entity.workCategory.WorkPartEntity
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_COLOR_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_LABEL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_DESCRIPTION_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_NAME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_REPETITION_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_WEIGHT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_TIME_STAMP_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_URL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WEEKDAY_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WORK_PART_NAME_DATA
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toLocalDateTime

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
            id = FAKE_INT_DATA,
            name = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            hint = FAKE_STRING_DATA,
            url = FAKE_URL_DATA,
            color = FAKE_COLOR_DATA,
            backgroundColor = FAKE_COLOR_DATA,
            badgeTimeStamp = FAKE_TIME_STAMP_DATA.toLocalDateTime(),
            mainFlag = DefaultDataGenerator.FAKE_BOOLEAN_DATA
        )

        val badgeEntityList = listOf(badgeEntity)
        val userBadgeEntityList = listOf(userBadgeEntity)

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