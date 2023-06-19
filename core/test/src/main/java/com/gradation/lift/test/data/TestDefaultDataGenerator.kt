package com.gradation.lift.test.data

import kotlinx.datetime.LocalDate

object TestDefaultDataGenerator {

    const val FAKE_INT_DATA = 0
    const val FAKE_STRING_DATA = ""
    val FAKE_DATE_DATA1 = LocalDate(year = 2023, monthNumber = 5, dayOfMonth = 25)
    val FAKE_DATE_DATA2 = LocalDate(year = 2023, monthNumber = 3, dayOfMonth = 31)

}