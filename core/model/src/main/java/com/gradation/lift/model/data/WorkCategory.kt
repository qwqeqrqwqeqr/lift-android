package com.gradation.lift.model.data

import kotlinx.serialization.SerialName

data class WorkCategory(
    val id: Int,
    val name: String="",
    val workpart: WorkPart?,
    val shortDescription: String = "",
    val longDescription: String = ""
) {

}