package com.gradation.lift.database.data

import com.gradation.lift.database.entity.badge.BadgeEntity
import com.gradation.lift.database.entity.history.HistoryEntity
import com.gradation.lift.database.entity.history.HistoryRoutineEntity
import com.gradation.lift.database.entity.routine.RoutineEntity
import com.gradation.lift.database.entity.routine.RoutineSetRoutineEntity
import com.gradation.lift.database.entity.userBadge.UserBadgeEntity
import com.gradation.lift.database.entity.work.WorkEntity
import com.gradation.lift.database.entity.work.WorkRoutineEntity
import com.gradation.lift.database.entity.workCategory.WorkCategoryEntity
import com.gradation.lift.database.entity.workCategory.WorkPartEntity
import com.gradation.lift.model.model.work.CheckedWorkSetInfo
import com.gradation.lift.model.model.work.EffectContent
import com.gradation.lift.model.model.work.SequenceContent
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_COLOR_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_LABEL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_LONG_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_PROGRESS_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_DESCRIPTION_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_NAME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_REPETITION_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_WEIGHT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_SCORE_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_TIME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_TIME_STAMP_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_URL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WEEKDAY_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WORK_CATEGORY_NAME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WORK_PART_NAME_DATA
import kotlinx.datetime.LocalTime.Companion.fromSecondOfDay
import kotlinx.datetime.toLocalDateTime

object TestDataGenerator {
    const val TEST_DATABASE = "test_database"


    object Badge {
        internal val BADGE_ENTITY = BadgeEntity(
            id = FAKE_INT_DATA,
            name = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            hint = FAKE_STRING_DATA,
            url = FAKE_URL_DATA,
            color = FAKE_COLOR_DATA,
            backgroundColor = FAKE_COLOR_DATA
        )

        internal val USER_BADGE_ENTITY = UserBadgeEntity(
            id = FAKE_INT_DATA,
            name = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            hint = FAKE_STRING_DATA,
            url = FAKE_URL_DATA,
            color = FAKE_COLOR_DATA,
            backgroundColor = FAKE_COLOR_DATA,
            badgeTimeStamp = FAKE_TIME_STAMP_DATA.toLocalDateTime(),
            mainFlag = FAKE_BOOLEAN_DATA
        )
    }


    object WorkPart {
        internal val WORK_PART_ENTITY =
            WorkPartEntity(
                id = FAKE_INT_DATA,
                name = FAKE_WORK_PART_NAME_DATA
            )
    }

    object WorkCategory {
        internal val WORK_CATEGORY_ENTITY = WorkCategoryEntity(
            id = FAKE_INT_DATA,
            name = FAKE_WORK_CATEGORY_NAME_DATA,
            equipment = FAKE_STRING_DATA,
            searchTag = listOf(FAKE_STRING_DATA),
            workPart = listOf(FAKE_WORK_PART_NAME_DATA),
            introduce = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            sequence = listOf(
                SequenceContent(
                    FAKE_INT_DATA,
                    FAKE_STRING_DATA,
                    FAKE_STRING_DATA
                )
            ),
            effect = listOf(
                EffectContent(
                    FAKE_STRING_DATA,
                    FAKE_STRING_DATA
                )
            ),
            caution = listOf(FAKE_STRING_DATA)
        )
    }


    object History {

        internal val HISTORY_ENTITY = HistoryEntity(
            id = FAKE_INT_DATA,
            comment = FAKE_STRING_DATA,
            score = FAKE_SCORE_DATA,
            progress = FAKE_PROGRESS_DATA,
            workTime = fromSecondOfDay(FAKE_TIME_DATA),
            restTime = fromSecondOfDay(FAKE_TIME_DATA),
            totalTime = fromSecondOfDay(FAKE_TIME_DATA),
            historyTimeStamp = FAKE_TIME_STAMP_DATA.toLocalDateTime(),
        )

        internal val HISTORY_ROUTINE_ENTITY = HistoryRoutineEntity(
            id = FAKE_INT_DATA,
            historyId = FAKE_INT_DATA,
            workCategoryId = FAKE_INT_DATA,
            workCategoryName = FAKE_WORK_CATEGORY_NAME_DATA,
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

    }


    object Routine {
        val ROUTINE_ENTITY = RoutineEntity(
            id = FAKE_INT_DATA,
            routineSetId = FAKE_INT_DATA,
            workCategoryId = FAKE_INT_DATA,
            workCategoryName = FAKE_WORK_CATEGORY_NAME_DATA,
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
        val ROUTINE_SET_ROUTINE_ENTITY = RoutineSetRoutineEntity(
            id = FAKE_INT_DATA,
            name = FAKE_ROUTINE_NAME_DATA,
            description = FAKE_ROUTINE_DESCRIPTION_DATA,
            weekday = listOf(FAKE_WEEKDAY_DATA),
            label = listOf(FAKE_LABEL_DATA),
            count = FAKE_INT_DATA,
            picture = FAKE_URL_DATA,
        )
    }

    object Work {
        val WORK_ENTITY = WorkEntity(
            id = FAKE_LONG_DATA,
            workTime = fromSecondOfDay(FAKE_TIME_DATA),
            restTime = fromSecondOfDay(FAKE_TIME_DATA),
            totalTime = fromSecondOfDay(FAKE_TIME_DATA),
            usedRoutineSetIdList = listOf(FAKE_INT_DATA),
            checkedWorkSetInfoList = listOf(
                CheckedWorkSetInfo(FAKE_INT_DATA, FAKE_INT_DATA)
            )
        )
        val WORK_ROUTINE_ENTITY = WorkRoutineEntity(
            workId = FAKE_LONG_DATA,
            workCategoryId = FAKE_INT_DATA,
            workCategoryName = FAKE_WORK_CATEGORY_NAME_DATA,
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
    }

}