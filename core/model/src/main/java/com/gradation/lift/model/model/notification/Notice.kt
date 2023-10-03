package com.gradation.lift.model.model.notification

import kotlinx.datetime.LocalDate

data class Notice(
    val title: String = "",
    val description: String = "",
    val date: LocalDate = LocalDate(2000, 1, 1)
)
