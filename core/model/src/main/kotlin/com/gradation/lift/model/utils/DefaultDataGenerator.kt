package com.gradation.lift.model.utils

import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.routine.getId
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.UnitOfWeight


object DefaultDataGenerator {

    const val FAKE_INT_DATA = 13
    const val FAKE_BOOLEAN_DATA = true
    const val FAKE_STRING_DATA = "lift"
    const val FAKE_URL_DATA = "https://deletedy37t5u9cq.pkw"

    const val FAKE_EMAIL_DATA = "emilyann_howes5h@schedules.kg"
    const val FAKE_PASSWORD_DATA = "Pass88#worD"

    const val FAKE_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiTcxMzcyMSJ9"
    const val FAKE_REFRESH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."

    const val FAKE_GENDER_DATA = Gender.MALE_VALUE
    const val FAKE_HEIGHT_DATA = 180.0f
    const val FAKE_WEIGHT_DATA = 85.5f
    const val FAKE_UNIT_OF_WEIGHT_DATA = UnitOfWeight.KG_VALUE
    const val FAKE_AUTHENTICATION_METHOD_DATA = LoginMethod.GOOGLE_VALUE

    const val FAKE_ROUTINE_NAME_DATA = "월요일 루틴"
    const val FAKE_ROUTINE_DESCRIPTION_DATA = "집중적으로 하는 운동"

    const val FAKE_COLOR_DATA = "#FFFFFFFF"
    const val FAKE_ERROR_MESSAGE = "ERROR"
    const val FAKE_TIME_STAMP_DATA = "2023-08-31T00:00"
    const val FAKE_DATE_DATA = "2023-08-31"
    const val FAKE_WEEKDAY_DATA = Weekday.MONDAY_VALUE
    val FAKE_LABEL_DATA = Label.LABEL1.getId()

    const val FAKE_SCORE_DATA = 5
    const val FAKE_PROGRESS_DATA = 55
    const val FAKE_TIME_DATA = 1200

    const val FAKE_ROUTINE_WEIGHT_DATA = 45.5f
    const val FAKE_ROUTINE_REPETITION_DATA = 12
    const val FAKE_WORK_CATEGORY_NAME_DATA = "운동"
    const val FAKE_WORK_PART_NAME_DATA = "운동"

    const val FAKE_STATUS_DATA = true
    const val FAKE_MESSAGE_DATA = ""

//    val FAKE_UUID_DATA:UUID = UUID(byteArrayOf(Byte))
}