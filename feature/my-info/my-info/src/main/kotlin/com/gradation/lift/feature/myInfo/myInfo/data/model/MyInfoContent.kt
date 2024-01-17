package com.gradation.lift.feature.myInfo.myInfo.data.model

data class MyInfoContent(
    val icon: Int,
    val content: String,
    val navigate: (() -> Unit)?,
)
