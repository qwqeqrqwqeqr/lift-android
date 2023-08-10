package com.gradation.lift.model.user


/**
 * [Gender]
 * 성별 정보를 담아둔 모델
 * [String] 을 통해 [Gender] 접근
 * [getGenderName] 또는 [getGenderValue]를 통해 [String]으로 변환
 */
sealed class Gender{
    data class Male(val value: String = MALE_VALUE, val name: String = MALE_NAME_VALUE) : Gender()
    data class Female(val value: String = FEMALE_VALUE, val name: String = FEMALE_NAME_VALUE) : Gender()

    companion object{
        const val MALE_VALUE = "male"
        const val FEMALE_VALUE = "female"
        const val MALE_NAME_VALUE = "남성"
        const val FEMALE_NAME_VALUE = "여성"
    }

    fun getGenderValue(): String = when(this){
        is Female -> this.value
        is Male -> this.value
    }

    fun getGenderName(): String = when(this){
        is Female -> this.name
        is Male -> this.name
    }
}

fun String?.toGender() : Gender =
    when(this){
        Gender.MALE_VALUE -> Gender.Male()
        Gender.FEMALE_VALUE -> Gender.Female()
        else -> Gender.Male()
    }