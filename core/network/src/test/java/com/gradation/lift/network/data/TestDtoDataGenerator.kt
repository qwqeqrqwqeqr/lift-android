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
import com.gradation.lift.network.dto.routine.*
import com.gradation.lift.network.dto.user.*
import com.gradation.lift.network.dto.work.*
import com.gradation.lift.test.data.TestDefaultDataGenerator
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_ACCESS_TOKEN
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_REFRESH_TOKEN

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

    object Routine{
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

    object RoutineSet{
        internal val routineSetDto1 = RoutineSetDto(
            routineSetId = 1,
            name = "행복한 월요일 루틴",
            description =  "가볍게 하는 운동",
            weekday = Weekday.MONDAY,
            picture = null
        )
        internal val routineSetDto2 = RoutineSetDto(
            routineSetId = 2,
            name = "등 단련 루틴",
            description =  "집중 등 운동",
            weekday = Weekday.MONDAY,
            picture = null
        )

        val createRoutineSetRequestDto = CreateRoutineSetRequestDto(
            name =  TestDefaultDataGenerator.FAKE_STRING_DATA,
            description = TestDefaultDataGenerator.FAKE_STRING_DATA,
            weekday = listOf(Weekday.MONDAY, Weekday.TUESDAY),
            picture = null,
            routine = listOf(createRoutineDto)
        )

        val createRoutineSetResponseDto = CreateRoutineSetResponseDto(result = TestDefaultDataGenerator.FAKE_BOOLEAN_DATA)


    }

    object RoutineSetRoutine{
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


    object User{
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