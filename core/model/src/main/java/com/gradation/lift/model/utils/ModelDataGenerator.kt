package com.gradation.lift.model.utils

import com.gradation.lift.model.BuildConfig
import com.gradation.lift.model.auth.SignInInfo
import com.gradation.lift.model.auth.SignUpInfo
import com.gradation.lift.model.auth.Token
import com.gradation.lift.model.common.UnitOfWeight
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.history.CreateHistory
import com.gradation.lift.model.history.CreateHistoryRoutine
import com.gradation.lift.model.history.History
import com.gradation.lift.model.history.HistoryRoutine
import com.gradation.lift.model.picture.RoutineSetPicture
import com.gradation.lift.model.picture.UserProfilePicture
import com.gradation.lift.model.routine.*
import com.gradation.lift.model.user.Email
import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.user.Name
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.model.work.WorkSet
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ACCESS_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_EMAIL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_PASSWORD_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_REFRESH_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_URL_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.routineModel1
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.routineModel2
import com.gradation.lift.model.utils.ModelDataGenerator.WorkCategory.workCategoryModel1
import com.gradation.lift.model.utils.ModelDataGenerator.WorkCategory.workCategoryModel2
import com.gradation.lift.model.utils.ModelDataGenerator.WorkPart.workPartModel1
import com.gradation.lift.model.utils.ModelDataGenerator.WorkPart.workPartModel2
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

object ModelDataGenerator {

    object Auth {
        val signInInfoModel = SignInInfo(
            id = FAKE_EMAIL_DATA,
            password = FAKE_PASSWORD_DATA
        )
        val signUpInfoModel = SignUpInfo(
            id = FAKE_EMAIL_DATA,
            password = FAKE_PASSWORD_DATA
        )
        val tokenModel = Token(
            accessToken = FAKE_ACCESS_TOKEN,
            refreshToken = FAKE_REFRESH_TOKEN
        )
    }

    object User {
        val emailModel = Email(FAKE_EMAIL_DATA)
        val nameModel = Name(FAKE_STRING_DATA)

        val userDetailModel = UserDetail(
            name = FAKE_STRING_DATA,
            gender = Gender.Male(),
            height = 180.0f,
            weight = 83.3f,
            profilePicture = BuildConfig.LIFT_S3_URL+FAKE_URL_DATA,
            unitOfWeight = UnitOfWeight.Kg()
        )

        val createUserDetailModel = UserDetail(
            name = FAKE_STRING_DATA,
            gender = Gender.Male(),
            height = 180.0f,
            weight = 83.3f,
            profilePicture = BuildConfig.LIFT_S3_URL+FAKE_URL_DATA,
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
            restTime = LocalTime(0, 10, 0),
            totalTime = LocalTime(0, 30, 0),
            historyTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
            historyRoutine = listOf(historyRoutineModel1)
        )

        val historyModel2 = History(
            historyId = 2,
            comment = "행복한 하루",
            score = 2,
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
            restTime = LocalTime(0, 10, 0),
            totalTime = LocalTime(0, 30, 0),
            historyTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
            historyRoutine = listOf(createHistoryRoutineModel)
        )

    }

    object Picture {
        val userProfilePictureModel1 = UserProfilePicture(
            id = 1,
            url = BuildConfig.LIFT_S3_URL+FAKE_URL_DATA
        )
        val userProfilePictureModel2 = UserProfilePicture(
            id = 2,
            url = BuildConfig.LIFT_S3_URL+FAKE_URL_DATA
        )

        val routineSetPictureModel1 = RoutineSetPicture(
            id = 1,
            category = FAKE_STRING_DATA,
            url = BuildConfig.LIFT_S3_URL+FAKE_URL_DATA
        )
        val routineSetPictureModel2 = RoutineSetPicture(
            id = 2,
            category = FAKE_STRING_DATA,
            url = BuildConfig.LIFT_S3_URL+FAKE_URL_DATA
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
            workPart = workPartModel1,
            introduce = "Lorem ipsum dolor sit amet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        )
        val workCategoryModel2 = WorkCategory(
            id = 2,
            name = "데드리프트",
            workPart = workPartModel2,
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

    object RoutineSet {
        val routineSetModel1 = RoutineSet(
            id = 1,
            name = "행복한 월요일 루틴",
            description = "가볍게 하는 운동",
            weekday = Weekday.Monday()
        )
        val routineSetModel2 = RoutineSet(
            id = 2,
            name = "등 단련 루틴",
            description = "집중 등 운동",
            weekday = Weekday.Monday()
        )
        val routineSetModelList = listOf(routineSetModel1, routineSetModel2)

    }

    object RoutineSetRoutine {
        val routineSetRoutineModel1 = RoutineSetRoutine(
            id = 1,
            name = "행복한 월요일 루틴",
            description = "가볍게 하는 운동",
            weekday = Weekday.Monday(),
            picture = null,
            routine = listOf(routineModel1)
        )

        val routineSetRoutineModel2 = RoutineSetRoutine(
            id = 2,
            name = "등 단련 루틴",
            description = "집중 등 운동",
            weekday = Weekday.Monday(),
            picture = null,
            routine = listOf(routineModel2)
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
            weekday = listOf(Weekday.Monday(), Weekday.Tuesday()),
            picture = "http://",
            routine = listOf(createRoutineModel),
        )
    }


}



