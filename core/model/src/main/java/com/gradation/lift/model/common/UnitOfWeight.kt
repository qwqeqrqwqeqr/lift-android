package com.gradation.lift.model.common

import com.gradation.lift.model.user.Gender

sealed class UnitOfWeight {
    data class Kg(val value: String = KG_VALUE, val name: String = "킬로") : UnitOfWeight()
    data class Lb(val value: String = LB_VALUE, val name: String = "파운드") : UnitOfWeight()

    companion object {
        const val KG_VALUE = "kg"
        const val LB_VALUE = "lb"
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
