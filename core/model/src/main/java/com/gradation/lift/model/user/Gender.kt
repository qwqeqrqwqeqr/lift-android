package com.gradation.lift.model.user

sealed class Gender{
    data class Male(val value: String = MALE_VALUE, val name: String = "남성") : Gender()
    data class Female(val value: String = FEMALE_VALUE, val name: String = "여성") : Gender()

    companion object{
        const val MALE_VALUE = "male"
        const val FEMALE_VALUE = "female"
    }
}