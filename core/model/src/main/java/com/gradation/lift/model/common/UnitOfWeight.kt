package com.gradation.lift.model.common

/**
 * [UnitOfWeight]
 * 운동 단위를 표현하는 모델
 * Kg, Lb 2가지의 단위가 존재한다.
 */
sealed class UnitOfWeight {
    data class Kg(val value: String = KG_VALUE, val name: String = KG_NAME_VALUE) : UnitOfWeight()
    data class Lb(val value: String = LB_VALUE, val name: String = LB_NAME_VALUE) : UnitOfWeight()

    companion object {
        const val KG_VALUE = "kg"
        const val LB_VALUE = "lb"
        const val KG_NAME_VALUE = "킬로"
        const val LB_NAME_VALUE = "파운드"
    }

    fun getUnitOfWeightValue(): String = when (this) {
        is Kg -> this.value
        is Lb -> this.value
    }

    fun getUnitOfWeightName(): String = when (this) {
        is Kg -> this.name
        is Lb -> this.name
    }
}


fun String?.toUnitOfWeight(): UnitOfWeight =
    when (this) {
        UnitOfWeight.KG_VALUE -> UnitOfWeight.Kg()
        UnitOfWeight.LB_VALUE -> UnitOfWeight.Lb()
        else -> UnitOfWeight.Kg()
    }
