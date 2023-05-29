package com.gradation.lift.domain.model.work

import com.gradation.lift.domain.model.common.DataState

sealed class WorkPart{
    data class Shoulder(val name:String="어깨") : WorkPart()
    data class Back(val name:String="등") : WorkPart()
    data class Chest(val name:String="가슴") : WorkPart()
    data class Arm(val name:String="팔") : WorkPart()
    data class LowerBody(val name:String="하체") : WorkPart()
}

const val SHOULDER =1
const val BACK = 2
const val CHEST = 3
const val ARM = 4
const val LOWER_BODY= 5