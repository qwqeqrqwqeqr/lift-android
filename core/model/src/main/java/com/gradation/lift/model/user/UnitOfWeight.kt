package com.gradation.lift.model.user

sealed class UnitOfWeight{
   data class KG(val value: String = "kg", val name: String = "킬로") : UnitOfWeight()
   data class LB(val value: String = "lb", val name: String = "파운드") : UnitOfWeight()
}