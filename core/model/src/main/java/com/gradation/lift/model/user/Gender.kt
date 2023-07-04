package com.gradation.lift.model.user

sealed class Gender{
    data class MALE(val value: String = MALE_VALUE, val name: String = "남성") : Gender()
    data class FEMALE(val value: String = FEMALE_VALUE, val name: String = "여성") : Gender()

    companion object{
        const val MALE_VALUE = "male"
        const val FEMALE_VALUE = "female"
    }
}