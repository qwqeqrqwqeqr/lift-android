package com.gradation.lift.model.model.routine

enum class Label(val id: Int) {
    LABEL1(id = 1),
    LABEL2(id = 2),
    LABEL3(id = 3),
    LABEL4(id = 4),
    LABEL5(id = 5)
}

fun String.toLabel() = when (this) {
    "1" -> Label.LABEL1
    "2" -> Label.LABEL2
    "3" -> Label.LABEL3
    "4" -> Label.LABEL4
    else -> Label.LABEL5
}

