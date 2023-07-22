package com.gradation.lift.network.data

import com.gradation.lift.model.common.Weekday.Companion.MONDAY
import com.gradation.lift.model.common.Weekday.Companion.TUESDAY
import com.gradation.lift.network.data.TestWorkDataGenerator.workCategoryDto1
import com.gradation.lift.network.data.TestWorkDataGenerator.workCategoryDto2
import com.gradation.lift.network.dto.routine.*
import com.gradation.lift.test.data.TestDefaultDataGenerator
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_BOOLEAN_DATA


internal object TestRoutineDataGenerator {


    private val routineDto1 = RoutineDto(
        routineId = 1,
        routineSetId = 1,
        workCategory = workCategoryDto1,
        workWeightList = listOf(10f, 10f, 10f, 10f, 10f),
        workRepetitionList = listOf(12, 12, 12, 12, 12),
    )
    private val routineDto2 = RoutineDto(
        routineId = 2,
        routineSetId = 2,
        workCategory = workCategoryDto2,
        workWeightList = listOf(10f, 10f, 10f, 10f, 10f),
        workRepetitionList = listOf(12, 12, 12, 12, 12),
    )

    private val createRoutineDto = CreateRoutineDto(
        workCategory = 1,
        workWeightList = listOf(10f, 10f, 10f, 10f, 10f),
        workRepetitionList = listOf(12, 12, 12, 12, 12),
    )

    private val routineSetDto1 = RoutineSetDto(
        routineSetId = 1,
        name = "행복한 월요일 루틴",
        description =  "가볍게 하는 운동",
        weekday = MONDAY,
        picture = null
    )
    private val routineSetDto2 = RoutineSetDto(
        routineSetId = 2,
        name = "등 단련 루틴",
        description =  "집중 등 운동",
        weekday = MONDAY,
        picture = null
    )

    private val routineSetRoutineDto1 = RoutineSetRoutineDto(
        routineDto = routineDto1,
        routineSetDto = routineSetDto1
    )

    private val routineSetRoutineDto2 = RoutineSetRoutineDto(
        routineDto = routineDto2,
        routineSetDto = routineSetDto2
    )

    val routineResponseJson: String = """
                       {
                  "status": true,
                  "message": "",
                  "data": {
                    "routine": [
                      {
                        "routine_id": 1,
                        "routine_set_id": 1,
                        "work_category": {
                          "id": 1,
                          "name": "숄더프레스",
                          "workpart": {
                            "id": 1,
                            "name": "어깨"
                          },
                          "introduce": "Lorem ipsum dolor sit amet",
                          "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                        },
                        "work_weight_list": [
                          10,
                          10,
                          10,
                          10,
                          10
                        ],
                        "work_repetition_list": [
                          12,
                          12,
                          12,
                          12,
                          12
                        ]
                      },
                      {
                        "routine_id": 2,
                        "routine_set_id": 2,
                        "work_category": {
                          "id": 2,
                          "name": "데드리프트",
                          "workpart": {
                            "id": 2,
                            "name": "등"
                          },
                          "introduce": "Lorem ipsum dolor sit amet",
                          "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                        },
                        "work_weight_list": [
                          10,
                          10,
                          10,
                          10,
                          10
                        ],
                        "work_repetition_list": [
                          12,
                          12,
                          12,
                          12,
                          12
                        ]
                      }
                    ]
                  }
                }
    """.trimIndent()

    val routineSetRoutineResponseJson: String = """
        {
          "status": true,
          "message": "",
          "data": {
            "routine_set_routine": [
              {
                "routine": {
                  "routine_id": 1,
                  "routine_set_id": 1,
                  "work_category": {
                    "id": 1,
                    "name": "숄더프레스",
                    "workpart": {
                      "id": 1,
                      "name": "어깨"
                    },
                    "introduce": "Lorem ipsum dolor sit amet",
                    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                  },
                  "work_weight_list": [
                    10,
                    10,
                    10,
                    10,
                    10
                  ],
                  "work_repetition_list": [
                    12,
                    12,
                    12,
                    12,
                    12
                  ]
                },
                "routine_set": {
                  "routine_set_id": 1,
                  "name": "행복한 월요일 루틴",
                  "description": "가볍게 하는 운동",
                  "weekday": "Mon",
                  "picture": null
                }
              },
              {
                "routine": {
                  "routine_id": 2,
                  "routine_set_id": 2,
                  "work_category": {
                    "id": 2,
                    "name": "데드리프트",
                    "workpart": {
                      "id": 2,
                      "name": "등"
                    },
                    "introduce": "Lorem ipsum dolor sit amet",
                    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                  },
                  "work_weight_list": [
                    10,
                    10,
                    10,
                    10,
                    10
                  ],
                  "work_repetition_list": [
                    12,
                    12,
                    12,
                    12,
                    12
                  ]
                },
                "routine_set": {
                  "routine_set_id": 2,
                  "name": "등 단련 루틴",
                  "description": "집중 등 운동",
                  "weekday": "Mon",
                  "picture": null
                }
              }
            ]
          }
        }
    """.trimIndent()


    val resultResponseJson: String = """
        {
          "status": true,
          "message": "",
          "data": {
            "result": true
          }
        }
    """.trimIndent()


    val createRoutineSetRequestDto = CreateRoutineSetRequestDto(
        name =  TestDefaultDataGenerator.FAKE_STRING_DATA,
        description = TestDefaultDataGenerator.FAKE_STRING_DATA,
        weekday = listOf(MONDAY, TUESDAY),
        picture = null,
        routine = listOf(createRoutineDto)
    )

    val createRoutineSetResponseDto = CreateRoutineSetResponseDto(result = FAKE_BOOLEAN_DATA)


    val getRoutineResponseDto = GetRoutineResponseDto(
        routine = listOf(
            routineDto1,
            routineDto2
        )
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

