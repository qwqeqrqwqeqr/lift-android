package com.gradation.lift.model.work


data class WorkCategory(
    val id: Int,
    val name: String,
    val workPart: WorkPart,
    val introduce: String,
    val description: String
) {

}