package com.gradation.lift.network.utils

import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkPart
import com.gradation.lift.network.dto.work.*


internal object TestDataGenerator {

    private val workPartModel1 = WorkPart(id = 1, name = "어꺠")
    private val workPartModel2 = WorkPart(id = 2, name = "등")

    private val workPartDto1 = WorkPartDto(id = 1, name = "어꺠")
    private val workPartDto2 = WorkPartDto(id = 2, name = "등")


    private val workCategoryModel1 = WorkCategory(
        id = 1,
        name = "숄더프레스",
        workpart = workPartModel1,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )
    private val workCategoryModel2 = WorkCategory(
        id = 2,
        name = "데드리프트",
        workpart = workPartModel2,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )


    private val workCategoryDto1 = WorkCategoryDto(
        id = 1,
        name = "숄더프레스",
        workpart = workPartDto1,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

    )

    private val workCategoryDto2 = WorkCategoryDto(
        id = 2,
        name = "데드리프트",
        workpart = workPartDto2,
        shortDescription = "Lorem ipsum dolor sit amet",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )



    val workPartModelList = listOf(workPartModel1, workPartModel2)
    val workCategoryModelList = listOf(workCategoryModel1, workCategoryModel2)

    val getWorkPartDto = GetWorkPartResponseDto(
        workpart = listOf(workPartDto1, workPartDto2)
    )

    val getWorkCategoryDto = GetWorkCategoryResponseDto(
        workCategory = listOf(workCategoryDto1, workCategoryDto2)
    )

    val getWorkCategoryByWorkPartDto = GetWorkCategoryByWorkPartResponseDto(
        workCategory = listOf(workCategoryDto1)
    )
}

