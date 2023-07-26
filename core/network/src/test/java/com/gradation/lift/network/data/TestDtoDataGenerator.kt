package com.gradation.lift.network.data

import com.gradation.lift.model.common.Weekday
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
import com.gradation.lift.test.data.TestDefaultDataGenerator
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_ACCESS_TOKEN
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_REFRESH_TOKEN
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_STRING_DATA
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

object TestDtoDataGenerator {


    object Auth {

        internal val signInDefaultResponseDto = SignInDefaultResponseDto(
            accessToken = FAKE_ACCESS_TOKEN,
            refreshToken = FAKE_REFRESH_TOKEN
        )

        internal val signInDefaultRequestDto = SignInDefaultRequestDto(
            id = TestDefaultDataGenerator.FAKE_STRING_DATA,
            password = TestDefaultDataGenerator.FAKE_STRING_DATA
        )

        internal val signUpDefaultRequestDto = SignUpDefaultRequestDto(
            email = TestDefaultDataGenerator.FAKE_STRING_DATA,
            password = TestDefaultDataGenerator.FAKE_STRING_DATA,
            name = TestDefaultDataGenerator.FAKE_STRING_DATA,
            phoneNumber = TestDefaultDataGenerator.FAKE_STRING_DATA
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
            restTime = LocalTime(0, 10, 0),
            totalTime = LocalTime(0, 30, 0),
            historyTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
            historyRoutine = historyRoutineDto1
        )
        internal val historyDto2 = HistoryDto(
            historyId = 2,
            comment = "행복한 하루",
            score = 4,
            restTime = LocalTime(0, 10, 0),
            totalTime = LocalTime(0, 30, 0),
            historyTimeStamp = LocalDateTime(2023, 8, 31, 0, 0, 0),
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
    }

    object Picture {
        internal val routineSetPictureDto1 = RoutineSetPictureDto(
            id = 1,
            category = FAKE_STRING_DATA,
            url = "http://"
        )
        internal val routineSetPictureDto2 = RoutineSetPictureDto(
            id = 2,
            category = FAKE_STRING_DATA,
            url = "http://"
        )

        internal val userProfilePictureDto1 = UserProfilePictureDto(
            id = 1,
            url = "http://"
        )
        internal val userProfilePictureDto2 = UserProfilePictureDto(
            id = 2,
            url = "http://"
        )

        internal val getRoutineSetPictureResponseDto =
            GetRoutineSetPictureResponseDto(listOf(routineSetPictureDto1, routineSetPictureDto2))

        internal val userProfilePictureResponseDto =
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
            name = "행복한 월요일 루틴",
            description = "가볍게 하는 운동",
            weekday = Weekday.MONDAY,
            picture = null
        )
        internal val routineSetDto2 = RoutineSetDto(
            routineSetId = 2,
            name = "등 단련 루틴",
            description = "집중 등 운동",
            weekday = Weekday.MONDAY,
            picture = null
        )

        val createRoutineSetRequestDto = CreateRoutineSetRequestDto(
            name = TestDefaultDataGenerator.FAKE_STRING_DATA,
            description = TestDefaultDataGenerator.FAKE_STRING_DATA,
            weekday = listOf(Weekday.MONDAY, Weekday.TUESDAY),
            picture = null,
            routine = listOf(createRoutineDto)
        )

        val createRoutineSetResponseDto =
            CreateRoutineSetResponseDto(result = TestDefaultDataGenerator.FAKE_BOOLEAN_DATA)

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
            name = "test",
            gender = "male",
            height = 180.0f,
            weight = 83.3f,
            profilePicture = null,
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