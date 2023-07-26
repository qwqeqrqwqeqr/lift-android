package com.gradation.lift.test.data

import kotlinx.datetime.LocalDate

object TestDefaultDataGenerator {

    const val FAKE_INT_DATA = 8888
    const val FAKE_BOOLEAN_DATA = true
    const val FAKE_STRING_DATA = "LIFT"
    val FAKE_DATE_DATA1 = LocalDate(year = 2023, monthNumber = 5, dayOfMonth = 25)
    val FAKE_DATE_DATA2 = LocalDate(year = 2023, monthNumber = 3, dayOfMonth = 31)


    const val FAKE_ACCESS_TOKEN =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODkxNDk1NTksInN1YiI6IjIwMTcxMzcyMSJ9.hW0xW24LoBbGKvftst-reNFdqxc2P89Q5sxJuSJOvGI"
    const val FAKE_REFRESH_TOKEN =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTE3MzQzNTksInN1YiI6IjIwMTcxMzcyMSJ9.CGSYkn3ilfDxD0J7P_XeVO1EA3k7g_tHEh6MHXAPEpk"



}