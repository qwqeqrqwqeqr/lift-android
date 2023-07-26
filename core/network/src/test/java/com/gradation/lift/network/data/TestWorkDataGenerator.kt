package com.gradation.lift.network.data

import com.gradation.lift.network.dto.work.*

internal object TestWorkDataGenerator {

    private val workPartDto1 = WorkPartDto(id = 1, name = "어깨")
    private val workPartDto2 = WorkPartDto(id = 2, name = "등")
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

    val workCategoryResponseJson: String = """
        {
          "status": true,
          "message": "",
          "data": {
            "work_category": [
              {
                "id": 1,
                "name": "숄더프레스",
                "work_part": {
                  "id": 1,
                  "name": "어깨"
                },
                "introduce": "Lorem ipsum dolor sit amet",
                "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
              },
              {
                "id": 2,
                "name": "데드리프트",
                "work_part": {
                  "id": 2,
                  "name": "등"
                },
                "introduce": "Lorem ipsum dolor sit amet",
                "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
              }
            ]
          }
        }
    """.trimIndent()

    val workPartResponseJson: String = """
        {
          "status": true,
          "message": "",
          "data": {
            "work_part": [
              {
                "id": 1,
                "name": "어깨"
              },
              {
                "id": 2,
                "name": "등"
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

    val getWorkPartResponseDto =
        GetWorkPartResponseDto(workPart = listOf(workPartDto1, workPartDto2))
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