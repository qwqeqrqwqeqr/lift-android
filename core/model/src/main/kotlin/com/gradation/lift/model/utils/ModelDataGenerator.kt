package com.gradation.lift.model.utils

import com.gradation.lift.model.BuildConfig
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.KakaoSignInInfo
import com.gradation.lift.model.model.auth.NaverSignInInfo
import com.gradation.lift.model.model.auth.Token
import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.model.model.badge.BadgeCondition
import com.gradation.lift.model.model.badge.CreateUserBadge
import com.gradation.lift.model.model.badge.UserBadge
import com.gradation.lift.model.model.common.UnitOfWeight
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.model.model.history.History
import com.gradation.lift.model.model.history.HistoryRoutine
import com.gradation.lift.model.model.notification.Notice
import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.model.model.routine.CreateRoutine
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateUsedRoutineSet
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ACCESS_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_COLOR_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_EMAIL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_PASSWORD_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_REFRESH_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_DESCRIPTION_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_NAME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_URL_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.routineModel1
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.routineModel2
import com.gradation.lift.model.utils.ModelDataGenerator.WorkCategory.workCategoryModel1
import com.gradation.lift.model.utils.ModelDataGenerator.WorkCategory.workCategoryModel2
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

object ModelDataGenerator {

    object Auth {
        val defaultSignInInfoModel = DefaultSignInInfo(
            id = FAKE_EMAIL_DATA,
            password = FAKE_PASSWORD_DATA
        )
        val defaultSignUpInfoModel = DefaultSignUpInfo(
            id = FAKE_EMAIL_DATA,
            password = FAKE_PASSWORD_DATA
        )
        val tokenModel = Token(
            accessToken = FAKE_ACCESS_TOKEN,
            refreshToken = FAKE_REFRESH_TOKEN
        )
        val kakaoSignInInfoModel = KakaoSignInInfo(
            id = FAKE_STRING_DATA,
            email = FAKE_EMAIL_DATA
        )
        val naverSignInInfoModel = NaverSignInInfo(
            id = FAKE_STRING_DATA,
            email = FAKE_EMAIL_DATA
        )
    }

    object Badge {
        val badgeModel = Badge(
            id = FAKE_INT_DATA,
            name = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            hint = FAKE_STRING_DATA,
            url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
            color = FAKE_COLOR_DATA
        )
        val badgeConditionModel = BadgeCondition(
            badge = badgeModel
        )
        val createUserBadgeModel = CreateUserBadge(
            id = FAKE_INT_DATA,
            badgeTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
        )
        val userBadgeModel = UserBadge(
            badge = badgeModel,
            badgeTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
            mainFlag = FAKE_BOOLEAN_DATA
        )
    }

    object User {

        val userDetailModel = UserDetail(
            name = FAKE_STRING_DATA,
            gender = Gender.Male(),
            height = 180.0f,
            weight = 83.3f,
            profilePicture = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
            unitOfWeight = UnitOfWeight.Kg()
        )

        val createUserDetailModel = UserDetail(
            name = FAKE_STRING_DATA,
            gender = Gender.Male(),
            height = 180.0f,
            weight = 83.3f,
            profilePicture = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
            unitOfWeight = UnitOfWeight.Kg()
        )
    }

    object History {

        val historyRoutineModel1 = HistoryRoutine(
            id = 1,
            historyId = 1,
            workCategory = workCategoryModel1,
            workSetList = listOf(
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12)
            )
        )

        val historyRoutineModel2 = HistoryRoutine(
            id = 2,
            historyId = 2,
            workCategory = workCategoryModel2,
            workSetList = listOf(
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12)
            )
        )


        val historyModel1 = History(
            historyId = 1,
            comment = "보람찬 하루",
            score = 5,
            workTime = LocalTime(0, 20, 0),
            restTime = LocalTime(0, 10, 0),
            totalTime = LocalTime(0, 30, 0),
            historyTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
            historyRoutine = listOf(historyRoutineModel1)
        )

        val historyModel2 = History(
            historyId = 2,
            comment = "행복한 하루",
            score = 2,
            workTime = LocalTime(0, 20, 0),
            restTime = LocalTime(0, 10, 0),
            totalTime = LocalTime(0, 30, 0),
            historyTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
            historyRoutine = listOf(historyRoutineModel2)
        )

        val historyModelList = listOf(historyModel1, historyModel2)


        val createHistoryRoutineModel = CreateHistoryRoutine(
            workCategory = "숄더프레스",
            workSetList = listOf(
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12)
            )
        )

        val createHistoryModel = CreateHistory(
            comment = "보람찬 하루",
            score = 5,
            progress =100,
            workTime = LocalTime(0, 20, 0),
            restTime = LocalTime(0, 10, 0),
            totalTime = LocalTime(0, 30, 0),
            historyTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
            historyRoutine = listOf(createHistoryRoutineModel)
        )

    }

    object Picture {
        val userProfilePictureModel1 = UserProfilePicture(
            id = 1,
            url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA
        )
        val userProfilePictureModel2 = UserProfilePicture(
            id = 2,
            url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA
        )

        val routineSetPictureModel1 = RoutineSetPicture(
            id = 1,
            category = FAKE_STRING_DATA,
            url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA
        )
        val routineSetPictureModel2 = RoutineSetPicture(
            id = 2,
            category = FAKE_STRING_DATA,
            url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA
        )


        val userProfilePictureModelList = listOf(
            userProfilePictureModel1, userProfilePictureModel2
        )
        val routineSetPictureModelList = listOf(
            routineSetPictureModel1, routineSetPictureModel2
        )
    }

    object WorkPart {
        val workPartModel1 = WorkPart(id = 1, name = "어깨")
        val workPartModel2 = WorkPart(id = 2, name = "등")
        val workPartModelList = listOf(workPartModel1, workPartModel2)
    }


    object WorkCategory {
        val workCategoryModel1 = WorkCategory(
            id = 1,
            name = "숄더프레스",
            workPart = listOf("어깨"),
            introduce = "Lorem ipsum dolor sit amet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        )
        val workCategoryModel2 = WorkCategory(
            id = 2,
            name = "데드리프트",
            workPart = listOf("등"),
            introduce = "Lorem ipsum dolor sit amet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        )

        val workCategoryModelList = listOf(workCategoryModel1, workCategoryModel2)

    }

    object Routine {
        val routineModel1 = Routine(
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
        val routineModel2 = Routine(
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
        val routineModelList = listOf(routineModel1, routineModel2)
    }

    object RoutineSetRoutine {
        val routineSetRoutineModel1 = RoutineSetRoutine(
            id = 1,
            name = FAKE_ROUTINE_NAME_DATA,
            description = FAKE_ROUTINE_DESCRIPTION_DATA,
            weekday = setOf(Weekday.Monday(), Weekday.Tuesday()),
            label = setOf(Label.LABEL1, Label.LABEL2),
            picture = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
            routine = listOf(routineModel1),
            count = FAKE_INT_DATA
        )

        val routineSetRoutineModel2 = RoutineSetRoutine(
            id = 2,
            name = FAKE_ROUTINE_NAME_DATA,
            description = FAKE_ROUTINE_DESCRIPTION_DATA,
            weekday = setOf(Weekday.Monday(), Weekday.Tuesday()),
            label = setOf(Label.LABEL1, Label.LABEL2),
            picture = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
            routine = listOf(routineModel2),
            count = FAKE_INT_DATA
        )
        val routineSetRoutineModelList = listOf(routineSetRoutineModel1, routineSetRoutineModel2)


        val createRoutineModel = CreateRoutine(
            workCategory = "숄더프레스",
            workSetList = listOf(
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12)
            )
        )

        val createRoutineSetRoutineModel = CreateRoutineSetRoutine(
            name = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            weekday = setOf(Weekday.Monday(), Weekday.Tuesday()),
            label = setOf(Label.LABEL1, Label.LABEL2),
            picture = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
            routine = listOf(createRoutineModel),
        )

        private val updateRoutineModel = UpdateRoutine(
            id = null,
            workCategory = "숄더프레스",
            workSetList = listOf(
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12),
                WorkSet(weight = 10f, repetition = 12)
            )
        )

        val updateRoutineSetRoutineModel = UpdateRoutineSetRoutine(
            id = FAKE_INT_DATA,
            name = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            weekday = setOf(Weekday.Monday(), Weekday.Tuesday()),
            label = setOf(Label.LABEL1, Label.LABEL2),
            picture = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
            routine = listOf(updateRoutineModel),
        )

        val updateUsedRoutineSetModel = UpdateUsedRoutineSet(
            routineSetIdList = listOf(FAKE_INT_DATA),
            usedTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
        )
    }

    object Notification {
        val noticeModel = Notice(
            id = FAKE_INT_DATA,
            title = FAKE_STRING_DATA,
            category ="update",
            description = FAKE_STRING_DATA,
            date = LocalDate(2023, 8, 31)
        )
    }
}



