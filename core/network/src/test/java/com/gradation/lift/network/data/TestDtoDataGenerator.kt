package com.gradation.lift.network.data

import com.gradation.lift.model.model.common.Weekday
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.createRoutineDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.routineDto1
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.routineDto2
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.routineSetDto1
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.routineSetDto2
import com.gradation.lift.network.data.TestDtoDataGenerator.WorkCategory.workCategoryDto1
import com.gradation.lift.network.data.TestDtoDataGenerator.WorkCategory.workCategoryDto2
import com.gradation.lift.network.data.TestDtoDataGenerator.WorkPart.workPartDto1
import com.gradation.lift.network.data.TestDtoDataGenerator.WorkPart.workPartDto2
import com.gradation.lift.network.dto.auth.*
import com.gradation.lift.network.dto.checker.CheckDuplicateEmailResponseDto
import com.gradation.lift.network.dto.checker.CheckDuplicateNameResponseDto
import com.gradation.lift.network.dto.history.*
import com.gradation.lift.network.dto.picture.GetRoutineSetPictureResponseDto
import com.gradation.lift.network.dto.picture.GetUserProfilePictureResponseDto
import com.gradation.lift.network.dto.picture.RoutineSetPictureDto
import com.gradation.lift.network.dto.picture.UserProfilePictureDto
import com.gradation.lift.network.dto.routine.*
import com.gradation.lift.network.dto.user.*
import com.gradation.lift.network.dto.work.*
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ACCESS_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_EMAIL_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_PASSWORD_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_REFRESH_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_DESCRIPTION_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ROUTINE_NAME_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_URL_DATA

object TestDtoDataGenerator {


    object Auth {

        internal val signInDefaultResponseDto = SignInDefaultResponseDto(
            accessToken = FAKE_ACCESS_TOKEN,
            refreshToken = FAKE_REFRESH_TOKEN
        )

        internal val signInDefaultRequestDto = SignInDefaultRequestDto(
            id = FAKE_EMAIL_DATA,
            password = FAKE_PASSWORD_DATA
        )

        internal val signUpDefaultRequestDto = SignUpDefaultRequestDto(
            email = FAKE_EMAIL_DATA,
            password = FAKE_PASSWORD_DATA,
            name = FAKE_STRING_DATA,
            phoneNumber = FAKE_STRING_DATA
        )
        internal val signUpDefaultResponseDto = SignUpDefaultResponseDto(
            result = true
        )
    }

    object Checker {

        internal val checkDuplicateNameResponseDto = CheckDuplicateNameResponseDto(
            result = true
        )

        internal val checkDuplicateEmailResponseDto = CheckDuplicateEmailResponseDto(
            result = true
        )
    }


    object History {

        internal val historyRoutineDto1 = HistoryRoutineDto(
            historyId = 1,
            historyRoutineId = 1,
            workCategory = workCategoryDto1,
            workWeightList = listOf(10f, 10f, 10f, 10f, 10f),
            workRepetitionList = listOf(12, 12, 12, 12, 12),
        )
        internal val historyRoutineDto2 = HistoryRoutineDto(
            historyId = 2,
            historyRoutineId = 2,
            workCategory = workCategoryDto2,
            workWeightList = listOf(10f, 10f, 10f, 10f, 10f),
            workRepetitionList = listOf(12, 12, 12, 12, 12),
        )

        internal val historyDto1 = HistoryDto(
            historyId = 1,
            comment = "보람찬 하루",
            score = 5,
            workTime = 1200,
            restTime = 600,
            totalTime = 1800,
            historyTimeStamp = "2023-08-31T00:00:00",
            historyRoutine = historyRoutineDto1
        )
        internal val historyDto2 = HistoryDto(
            historyId = 2,
            comment = "행복한 하루",
            score = 2,
            workTime = 1200,
            restTime = 600,
            totalTime = 1800,
            historyTimeStamp = "2023-08-31T00:00:00",
            historyRoutine = historyRoutineDto2
        )


        internal val createHistoryResponseDto = CreateHistoryResponseDto(FAKE_BOOLEAN_DATA)
        internal val deleteHistoryResponseDto = DeleteHistoryResponseDto(FAKE_BOOLEAN_DATA)
        internal val getHistoryResponseDto = GetHistoryResponseDto(listOf(historyDto1, historyDto2))
        internal val getHistoryByHistoryIdResponseDto = GetHistoryByHistoryIdResponseDto(
            listOf(
                historyDto1, historyDto2
            )
        )

        internal val createHistoryRoutineDto = CreateHistoryRoutineDto(
            workCategory = "숄더프레스",
            workWeightList = listOf(10f, 10f, 10f, 10f, 10f),
            workRepetitionList = listOf(12, 12, 12, 12, 12),
        )


        val createHistoryRequestDto = CreateHistoryRequestDto(
            comment = "보람찬 하루",
            score = 5,
            workTime = 1200,
            restTime = 600,
            totalTime = 1800,
            historyTimeStamp = "2023-08-31T00:00",
            historyRoutine = listOf(createHistoryRoutineDto)
        )
    }

    object Picture {
        internal val routineSetPictureDto1 = RoutineSetPictureDto(
            id = 1,
            category = FAKE_STRING_DATA,
            url = FAKE_URL_DATA
        )
        internal val routineSetPictureDto2 = RoutineSetPictureDto(
            id = 2,
            category = FAKE_STRING_DATA,
            url = FAKE_URL_DATA
        )

        internal val userProfilePictureDto1 = UserProfilePictureDto(
            id = 1,
            url = FAKE_URL_DATA
        )
        internal val userProfilePictureDto2 = UserProfilePictureDto(
            id = 2,
            url = FAKE_URL_DATA
        )

        internal val getRoutineSetPictureResponseDto =
            GetRoutineSetPictureResponseDto(listOf(routineSetPictureDto1, routineSetPictureDto2))

        internal val getUserProfilePictureResponseDto =
            GetUserProfilePictureResponseDto(listOf(userProfilePictureDto1, userProfilePictureDto2))

    }

    object Refresh {
        internal val refreshResponseDto = RefreshResponseDto(
            accessToken = FAKE_ACCESS_TOKEN
        )
    }


    object WorkPart {
        internal val workPartDto1 = WorkPartDto(id = 1, name = "어깨")
        internal val workPartDto2 = WorkPartDto(id = 2, name = "등")

        val getWorkPartResponseDto =
            GetWorkPartResponseDto(workPart = listOf(workPartDto1, workPartDto2))
    }

    object WorkCategory {
        internal val workCategoryDto1 = WorkCategoryDto(
            id = 1,
            name = "숄더프레스",
            workPart = workPartDto1,
            introduce = "Lorem ipsum dolor sit amet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

        )
        internal val workCategoryDto2 = WorkCategoryDto(
            id = 2,
            name = "데드리프트",
            workPart = workPartDto2,
            introduce = "Lorem ipsum dolor sit amet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        )

        val getWorkCategoryResponseDto =
            GetWorkCategoryResponseDto(workCategory = listOf(workCategoryDto1, workCategoryDto2))
        val getWorkCategoryByWorkPartResponseDto =
            GetWorkCategoryByWorkPartResponseDto(
                workCategory = listOf(
                    workCategoryDto1,
                    workCategoryDto2
                )
            )
    }

    object Routine {
        internal val routineDto1 = RoutineDto(
            routineId = 1,
            routineSetId = 1,
            workCategory = workCategoryDto1,
            workWeightList = listOf(10f, 10f, 10f, 10f, 10f),
            workRepetitionList = listOf(12, 12, 12, 12, 12),
        )
        internal val routineDto2 = RoutineDto(
            routineId = 2,
            routineSetId = 2,
            workCategory = workCategoryDto2,
            workWeightList = listOf(10f, 10f, 10f, 10f, 10f),
            workRepetitionList = listOf(12, 12, 12, 12, 12),
        )

        internal val createRoutineDto = CreateRoutineDto(
            workCategory = "숄더프레스",
            workWeightList = listOf(10f, 10f, 10f, 10f, 10f),
            workRepetitionList = listOf(12, 12, 12, 12, 12),
        )
        val getRoutineResponseDto = GetRoutineResponseDto(
            routine = listOf(
                routineDto1,
                routineDto2
            )
        )

    }


    object RoutineSet {
        internal val routineSetDto1 = RoutineSetDto(
            routineSetId = 1,
            name = FAKE_ROUTINE_NAME_DATA,
            description = FAKE_ROUTINE_DESCRIPTION_DATA,
            weekday = Weekday.MONDAY_VALUE,
            picture = FAKE_URL_DATA
        )
        internal val routineSetDto2 = RoutineSetDto(
            routineSetId = 2,
            name = FAKE_ROUTINE_NAME_DATA,
            description = FAKE_ROUTINE_DESCRIPTION_DATA,
            weekday = Weekday.MONDAY_VALUE,
            picture = FAKE_URL_DATA
        )

        val createRoutineSetRoutineRequestDto = CreateRoutineSetRoutineRequestDto(
            name = FAKE_STRING_DATA,
            description = FAKE_STRING_DATA,
            weekday = listOf(Weekday.MONDAY_VALUE, Weekday.TUESDAY_VALUE),
            picture = FAKE_URL_DATA,
            routine = listOf(createRoutineDto)
        )

        val createRoutineSetRoutineResponseDto =
            CreateRoutineSetRoutineResponseDto(result = FAKE_BOOLEAN_DATA)

    }


    object RoutineSetRoutine {
        internal val routineSetRoutineDto1 = RoutineSetRoutineDto(
            routineDto = routineDto1,
            routineSetDto = routineSetDto1
        )

        internal val routineSetRoutineDto2 = RoutineSetRoutineDto(
            routineDto = routineDto2,
            routineSetDto = routineSetDto2
        )

        val getRoutineSetRoutineResponseDto = GetRoutineSetRoutineResponseDto(
            routineSetRoutine = listOf(
                routineSetRoutineDto1,
                routineSetRoutineDto2,
            )
        )

        val getRoutineSetRoutineByRoutineSetIdResponseDto =
            GetRoutineSetRoutineByRoutineSetIdResponseDto(
                routineSetRoutine = listOf(
                    routineSetRoutineDto1,
                    routineSetRoutineDto2
                )
            )

        val getRoutineSetRoutineByWeekdayResponseDto = GetRoutineSetRoutineByWeekdayResponseDto(
            routineSetRoutine = listOf(
                routineSetRoutineDto1,
                routineSetRoutineDto2,
            )
        )

    }


    object User {
        val userDetailDto = UserDetailDto(
            name = FAKE_STRING_DATA,
            gender = "male",
            height = 180.0f,
            weight = 83.3f,
            profilePicture = FAKE_URL_DATA,
            unitOfWeight = "kg"
        )


        val createUserDetailRequestDto = CreateUserDetailRequestDto(userDetailDto)
        val createUserDetailResponseDto = CreateUserDetailResponseDto(true)
        val existUserDetailResponseDto = ExistUserDetailResponseDto(true)
        val getUserDetailResponseDto = GetUserDetailResponseDto(userDetailDto)
        val updateUserDetailRequestDto = UpdateUserDetailRequestDto(userDetailDto)
        val updateUserDetailResponseDto = UpdateUserDetailResponseDto(true)

    }


}