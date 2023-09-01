package com.gradation.lift.designsystem.chart.model

data class WorkHexagonChartItem(
    private val chest : String = "가슴",
    private val shoulder : String = "어꺠",
    private val arm : String = "팔",
    private val back : String = "등",
    private val lowerBody : String = "하체",
    private  val abs : String = "복근",

    val chestValue : Int = 0,
    val shoulderValue : Int = 0,
    val armValue : Int = 0,
    val backValue : Int = 0,
    val lowerBodyValue : Int = 0,
    val absValue : Int = 0,
)


