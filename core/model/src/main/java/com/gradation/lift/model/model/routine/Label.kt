package com.gradation.lift.model.model.routine

import com.gradation.lift.model.model.routine.Label.*

enum class Label(val id: Int) {
    LABEL1(id = 1),
    LABEL2(id = 2),
    LABEL3(id = 3),
    LABEL4(id = 4),
    LABEL5(id = 5)


}

fun String.toLabel() = when (this) {
    "1" -> LABEL1
    "2" -> LABEL2
    "3" -> LABEL3
    "4" -> LABEL4
    else -> LABEL5
}

fun Label.getId(): String = when (this) {
    LABEL1 -> id.toString()
    LABEL2 -> id.toString()
    LABEL3 -> id.toString()
    LABEL4 -> id.toString()
    LABEL5 -> id.toString()
}
