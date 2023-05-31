package com.gradation.lift.domain.model.work

data class WorkCategory(
    val id: Int,
    val name: String,
    val workpart: WorkPart,
    val shortDescription: String,
    val longDescription: String
) {

}