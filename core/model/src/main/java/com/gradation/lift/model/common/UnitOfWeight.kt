package com.gradation.lift.model.common

sealed class UnitOfWeight{
   data class Kg(val value: String = KG_VALUE, val name: String = "킬로") : UnitOfWeight()
   data class Lb(val value: String = LB_VALUE, val name: String = "파운드") : UnitOfWeight()

   companion object{
      const val KG_VALUE = "kg"
      const val LB_VALUE = "lb"
   }
}
