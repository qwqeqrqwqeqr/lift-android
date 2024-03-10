package com.gradation.lift.model.utils

import com.gradation.lift.model.BuildConfig
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.EmailAuthenticationInfo
import com.gradation.lift.model.model.auth.EmailAuthenticationValidationInfo
import com.gradation.lift.model.model.auth.GoogleSignInInfo
import com.gradation.lift.model.model.auth.GoogleSignUpInfo
import com.gradation.lift.model.model.auth.KakaoSignInInfo
import com.gradation.lift.model.model.auth.KakaoSignUpInfo
import com.gradation.lift.model.model.auth.NaverSignInInfo
import com.gradation.lift.model.model.auth.NaverSignUpInfo
import com.gradation.lift.model.model.auth.Token
import com.gradation.lift.model.model.auth.UpdatePasswordInfo
import com.gradation.lift.model.model.auth.toLoginMethod
import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.model.model.badge.BadgeCondition
import com.gradation.lift.model.model.badge.CreateUserBadge
import com.gradation.lift.model.model.badge.UpdateUserBadgeMainFlag
import com.gradation.lift.model.model.badge.UserBadge
import com.gradation.lift.model.model.date.toWeekDay
import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.model.model.history.History
import com.gradation.lift.model.model.history.HistoryRoutine
import com.gradation.lift.model.model.history.UpdateHistoryInfo
import com.gradation.lift.model.model.notice.Notice
import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.model.model.routine.CreateRoutine
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateUsedRoutineSet
import com.gradation.lift.model.model.routine.toLabel
import com.gradation.lift.model.model.user.DeleteUserInfo
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.model.model.user.UserDetailInfo
import com.gradation.lift.model.model.user.UserDetailName
import com.gradation.lift.model.model.user.UserDetailProfilePicture
import com.gradation.lift.model.model.user.toGender
import com.gradation.lift.model.model.work.CheckedWorkSetInfo
import com.gradation.lift.model.model.work.EffectContent
import com.gradation.lift.model.model.work.SequenceContent
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.model.model.work.WorkRoutine
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ACCESS_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_AUTHENTICATION_METHOD_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_COLOR_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_DATE_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_EMAIL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_GENDER_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_HEIGHT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_LABEL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_LONG_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_PASSWORD_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_PROGRESS_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_REFRESH_TOKEN
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
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WEIGHT_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WORK_CATEGORY_NAME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_WORK_PART_NAME_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.Weekday.WEEKDAY_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Work.WORK_SET_LIST_MODEL
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime.Companion.fromSecondOfDay
import kotlinx.datetime.toLocalDateTime

object ModelDataGenerator {

    object Auth {
        val DEFAULT_SIGN_IN_INFO_MODEL = DefaultSignInInfo(
            id = FAKE_EMAIL_DATA,
            password = FAKE_PASSWORD_DATA
        )
        val DEFAULT_SIGN_UP_INFO_MODEL = DefaultSignUpInfo(
            id = FAKE_EMAIL_DATA,
            password = FAKE_PASSWORD_DATA
        )
        val EMAIL_AUTHENTICATION_INFO_MODEL = EmailAuthenticationInfo(
            email = FAKE_EMAIL_DATA,
            isSigned = FAKE_BOOLEAN_DATA
        )
        val EMAIL_AUTHENTICATION_VALIDATION_INFO_MODEL = EmailAuthenticationValidationInfo(
            email = FAKE_EMAIL_DATA,
            authenticationCode = FAKE_INT_DATA,
            isSigned = FAKE_BOOLEAN_DATA
        )
        val GOOGLE_SIGN_IN_INFO_MODEL = GoogleSignInInfo(
            id = FAKE_STRING_DATA,
            email = FAKE_EMAIL_DATA,
        )
        val GOOGLE_SIGN_UP_INFO_MODEL = GoogleSignUpInfo(
            id = FAKE_STRING_DATA,
            email = FAKE_EMAIL_DATA,
        )
        val KAKAO_SIGN_IN_INFO_MODEL = KakaoSignInInfo(
            id = FAKE_STRING_DATA,
            email = FAKE_EMAIL_DATA
        )
        val KAKAO_SIGN_UP_INFO_MODEL = KakaoSignUpInfo(
            id = FAKE_STRING_DATA,
            email = FAKE_EMAIL_DATA
        )
        val LOGIN_METHOD_MODEL = FAKE_AUTHENTICATION_METHOD_DATA.toLoginMethod()

        val NAVER_SIGN_IN_INFO_MODEL = NaverSignInInfo(
            id = FAKE_STRING_DATA,
            email = FAKE_EMAIL_DATA
        )
        val NAVER_SIGN_UP_INFO_MODEL = NaverSignUpInfo(
            id = FAKE_STRING_DATA,
            email = FAKE_EMAIL_DATA
        )
        val TOKEN_MODEL = Token(
            accessToken = FAKE_ACCESS_TOKEN,
            refreshToken = FAKE_REFRESH_TOKEN
        )
        val UPDATE_PASSWORD_INFO_MODEL = UpdatePasswordInfo(
            email = FAKE_EMAIL_DATA,
            password = FAKE_PASSWORD_DATA
        )

    }

    object Badge {
        val BADGE_MODEL = Badge(
            id = FAKE_INT_DATA,
            name = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            hint = FAKE_STRING_DATA,
            url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
            color = FAKE_COLOR_DATA,
            backgroundColor = FAKE_COLOR_DATA
        )
        val BADGE_CONDITION_MODEL = BadgeCondition(
            badge = Badge(
                id = FAKE_INT_DATA,
                name = FAKE_STRING_DATA,
                description = FAKE_STRING_DATA,
                hint = FAKE_STRING_DATA,
                url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
                color = FAKE_COLOR_DATA,
                backgroundColor = FAKE_COLOR_DATA
            )
        )
        val CREATE_USER_BADGE_MODEL = CreateUserBadge(
            id = FAKE_INT_DATA,
            badgeTimeStamp = FAKE_TIME_STAMP_DATA.toLocalDateTime()
        )
        val UPDATE_USER_BADGE_MAIN_FLAG_MODEL = UpdateUserBadgeMainFlag(
            listOf(
                FAKE_INT_DATA to FAKE_BOOLEAN_DATA
            )
        )
        val USER_BADGE_MODEL = UserBadge(
            badge = Badge(
                id = FAKE_INT_DATA,
                name = FAKE_STRING_DATA,
                description = FAKE_STRING_DATA,
                hint = FAKE_STRING_DATA,
                url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
                color = FAKE_COLOR_DATA,
                backgroundColor = FAKE_COLOR_DATA
            ),
            badgeTimeStamp = FAKE_TIME_STAMP_DATA.toLocalDateTime(),
            mainFlag = FAKE_BOOLEAN_DATA
        )
    }

    object User {

        val DELETE_USER_INFO_MODEL = DeleteUserInfo(FAKE_STRING_DATA)
        val GENDER_MODEL = FAKE_GENDER_DATA.toGender()

        val USER_DETAIL_MODEL = UserDetail(
            name = FAKE_STRING_DATA,
            gender = GENDER_MODEL,
            height = FAKE_HEIGHT_DATA,
            weight = FAKE_WEIGHT_DATA,
            profilePicture = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
        )

        val USER_DETAIL_INFO_MODEL = UserDetailInfo(
            gender = GENDER_MODEL,
            height = FAKE_HEIGHT_DATA,
            weight = FAKE_WEIGHT_DATA,
        )

        val USER_DETAIL_NAME_MODEL = UserDetailName(
            name = FAKE_STRING_DATA
        )

        val USER_DETAIL_PROFILE_PICTURE_MODEL = UserDetailProfilePicture(
            profilePicture = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
        )


    }

    object WorkPart {
        val WORK_PART_MODEL = WorkPart(id = FAKE_INT_DATA, name = FAKE_WORK_PART_NAME_DATA)


    }

    object WorkCategory {
        val WORK_CATEGORY_MODEL = WorkCategory(
            id = FAKE_INT_DATA,
            name = FAKE_WORK_CATEGORY_NAME_DATA,
            equipment = FAKE_STRING_DATA,
            searchTag = listOf(FAKE_STRING_DATA),
            workPart = listOf(FAKE_WORK_PART_NAME_DATA),
            introduce = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            sequence = listOf(
                SequenceContent(
                    1,
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

    object Work {

        val WORK_SET_LIST_MODEL = listOf(
            WorkSet(
                workSetId = 0,
                weight = FAKE_ROUTINE_WEIGHT_DATA,
                repetition = FAKE_ROUTINE_REPETITION_DATA
            ),
            WorkSet(
                workSetId = 1,
                weight = FAKE_ROUTINE_WEIGHT_DATA,
                repetition = FAKE_ROUTINE_REPETITION_DATA
            ),
            WorkSet(
                workSetId = 2,
                weight = FAKE_ROUTINE_WEIGHT_DATA,
                repetition = FAKE_ROUTINE_REPETITION_DATA
            ),
        )


        val WORK_ROUTINE_MODEL = WorkRoutine(
            workId = FAKE_LONG_DATA,
            workRoutineId = FAKE_INT_DATA,
            workCategoryId = FAKE_INT_DATA,
            workCategoryName = FAKE_WORK_CATEGORY_NAME_DATA,
            workPart = listOf(FAKE_WORK_PART_NAME_DATA),
            workSetList = WORK_SET_LIST_MODEL
        )

        val WORK_MODEL = com.gradation.lift.model.model.work.Work(
            id = FAKE_LONG_DATA,
            workTime = fromSecondOfDay(FAKE_TIME_DATA),
            restTime = fromSecondOfDay(FAKE_TIME_DATA),
            totalTime = fromSecondOfDay(FAKE_TIME_DATA),
            routine = listOf(WORK_ROUTINE_MODEL),
            usedRoutineSetIdList = listOf(FAKE_INT_DATA),
            checkedWorkSetInfoList = listOf(
                CheckedWorkSetInfo(FAKE_INT_DATA, FAKE_INT_DATA)
            )
        )
    }


    object History {

        val CREATE_HISTORY_ROUTINE_MODEL = CreateHistoryRoutine(
            workCategory = FAKE_WORK_CATEGORY_NAME_DATA,
            workSetList = WORK_SET_LIST_MODEL
        )

        val CREATE_HISTORY_MODEL = CreateHistory(
            comment = FAKE_STRING_DATA,
            score = FAKE_SCORE_DATA,
            progress = FAKE_PROGRESS_DATA,
            workTime = fromSecondOfDay(FAKE_TIME_DATA),
            restTime = fromSecondOfDay(FAKE_TIME_DATA),
            totalTime = fromSecondOfDay(FAKE_TIME_DATA),
            historyTimeStamp = FAKE_TIME_STAMP_DATA.toLocalDateTime(),
            historyRoutine = listOf(CREATE_HISTORY_ROUTINE_MODEL)
        )


        val HISTORY_ROUTINE_MODEL = HistoryRoutine(
            id = FAKE_INT_DATA,
            historyId = FAKE_INT_DATA,
            workCategoryId = FAKE_INT_DATA,
            workCategoryName = FAKE_WORK_CATEGORY_NAME_DATA,
            workPart = listOf(FAKE_WORK_PART_NAME_DATA),
            workSetList = WORK_SET_LIST_MODEL
        )

        val HISTORY_MODEL = History(
            historyId = FAKE_INT_DATA,
            comment = FAKE_STRING_DATA,
            score = FAKE_SCORE_DATA,
            progress = FAKE_PROGRESS_DATA,
            workTime = fromSecondOfDay(FAKE_TIME_DATA),
            restTime = fromSecondOfDay(FAKE_TIME_DATA),
            totalTime = fromSecondOfDay(FAKE_TIME_DATA),
            historyTimeStamp = FAKE_TIME_STAMP_DATA.toLocalDateTime(),
            historyRoutine = listOf(HISTORY_ROUTINE_MODEL)
        )


        val UPDATE_HISTORY_INFO_MODEL = UpdateHistoryInfo(
            historyId = FAKE_INT_DATA,
            comment = FAKE_STRING_DATA,
            score = FAKE_SCORE_DATA,
        )

    }

    object Notice {
        val NOTICE_MODEL = Notice(
            id = FAKE_INT_DATA,
            title = FAKE_STRING_DATA,
            category = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            date = LocalDate.parse(FAKE_DATE_DATA)
        )
    }

    object Picture {
        val ROUTINE_SET_PICTURE_MODEL = RoutineSetPicture(
            id = FAKE_INT_DATA,
            category = FAKE_STRING_DATA,
            url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA
        )
        val USER_PROFILE_PICTURE_MODEL = UserProfilePicture(
            id = FAKE_INT_DATA,
            url = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA
        )
    }

    object Weekday {
        val WEEKDAY_MODEL = FAKE_WEEKDAY_DATA.toWeekDay()
    }


    object Routine {

        val LABEL_MODEL = FAKE_LABEL_DATA.toLabel()

        val ROUTINE_MODEL = Routine(
            id = FAKE_INT_DATA,
            routineSetId = FAKE_INT_DATA,
            workCategoryId = FAKE_INT_DATA,
            workCategoryName = FAKE_WORK_CATEGORY_NAME_DATA,
            workPart = listOf(FAKE_WORK_PART_NAME_DATA),
            workSetList = WORK_SET_LIST_MODEL
        )

        val ROUTINE_SET_ROUTINE_MODEL = RoutineSetRoutine(
            id = FAKE_INT_DATA,
            name = FAKE_ROUTINE_NAME_DATA,
            description = FAKE_ROUTINE_DESCRIPTION_DATA,
            weekday = setOf(WEEKDAY_MODEL),
            label = setOf(LABEL_MODEL),
            picture = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
            routine = listOf(ROUTINE_MODEL),
            count = FAKE_INT_DATA
        )

        val CREATE_ROUTINE_MODEL = CreateRoutine(
            workCategory = FAKE_WORK_CATEGORY_NAME_DATA,
            workSetList = WORK_SET_LIST_MODEL
        )

        val CREATE_ROUTINE_SET_ROUTINE_MODEL = CreateRoutineSetRoutine(
            name = FAKE_ROUTINE_NAME_DATA,
            description = FAKE_ROUTINE_DESCRIPTION_DATA,
            weekday = setOf(WEEKDAY_MODEL),
            label = setOf(LABEL_MODEL),
            picture = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
            routine = listOf(CREATE_ROUTINE_MODEL),
        )

        private val UPDATE_ROUTINE_MODEL = UpdateRoutine(
            id = FAKE_INT_DATA,
            workCategory = FAKE_WORK_CATEGORY_NAME_DATA,
            workSetList = WORK_SET_LIST_MODEL
        )

        val UPDATE_ROUTINE_SET_ROUTINE_MODEL = UpdateRoutineSetRoutine(
            id = FAKE_INT_DATA,
            name = FAKE_ROUTINE_NAME_DATA,
            description = FAKE_ROUTINE_DESCRIPTION_DATA,
            weekday = setOf(WEEKDAY_MODEL),
            label = setOf(LABEL_MODEL),
            picture = BuildConfig.LIFT_S3_URL + FAKE_URL_DATA,
            routine = listOf(UPDATE_ROUTINE_MODEL),
        )

        val UPDATE_USED_ROUTINE_SET_MODEL = UpdateUsedRoutineSet(
            routineSetIdList = listOf(FAKE_INT_DATA),
            usedTimeStamp = FAKE_TIME_STAMP_DATA.toLocalDateTime(),
        )
    }

}



