package com.gradation.lift.model.data

enum class WorkPart(val value: String){
    shoulder(value = "어깨"),
    back(value = "등"),
    chest(value = "기슴"),
    arm(value = "팔"),
    lowerBody(value = "하체"),
}


fun String.toWorkPartType() = when  {
    else -> WorkPart.values().first { type -> type.value == this }
}