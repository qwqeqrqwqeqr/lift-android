package com.gradation.lift.network.utils

import com.gradation.lift.domain.model.work.SHOULDER
import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkPart


internal object TestDataGenerator {

    val workPart1 = WorkPart.Shoulder()
    val workPart2 = WorkPart.Back()

    val workCategory1 = WorkCategory(
        id = 1,
        name = "숄더프레스",
        workpart = workPart1,
        shortDescription = "Lorem ipsum dolor sit amet,",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )
    val workCategory2 = WorkCategory(
        id = 2,
        name = "데드리프트",
        workpart = workPart2,
        shortDescription = "Lorem ipsum dolor sit amet,",
        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )



}

