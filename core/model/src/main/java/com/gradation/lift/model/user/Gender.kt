package com.gradation.lift.model.user

sealed class Gender{
    data class MALE(val value: String = "male", val name: String = "남성") : Gender()
    data class FEMALE(val value: String = "female", val name: String = "여성") : Gender()
}