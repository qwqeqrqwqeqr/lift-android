package com.gradation.lift.model.model.notice

import kotlinx.datetime.LocalDate

data class Notice(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val date: LocalDate = LocalDate(2000, 1, 1)
)
