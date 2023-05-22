package com.gradation.lift.domain.model

data class WorkCategory(
    val id: Int,
    val name: String="",
    val workpart: WorkPart?,
    val shortDescription: String = "",
    val longDescription: String = ""
) {

}