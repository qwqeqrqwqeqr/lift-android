package com.gradation.lift.domain.usecase

import com.gradation.lift.domain.utils.DataGenerator.TEST_DATE_1
import com.gradation.lift.domain.utils.DataGenerator.TEST_DATE_2
import com.gradation.lift.domain.usecase.date.getCurrentWeekDate
import kotlinx.datetime.LocalDate
import org.junit.Test
import org.junit.Assert.*

class DateUseCaseTest {


    @Test
    fun testGetCurrentWeekDate() {
        val getCurrentWeekDate1 = getCurrentWeekDate(TEST_DATE_1)
        val getCurrentWeekDate2 = getCurrentWeekDate(TEST_DATE_2)
        assertEquals(7, getCurrentWeekDate1.size)
        assertEquals(
            LocalDate(year = 2023, monthNumber = 5, dayOfMonth = 22),
            getCurrentWeekDate1.first()
        )
        assertEquals(
            LocalDate(year = 2023, monthNumber = 4, dayOfMonth = 2),
            getCurrentWeekDate2.last()
        )
    }
}