package com.gradation.lift.feature.myInfo.profile.data.model

data class ProfileContent(
    val title: String,
    val content: String?,
    val navigate: () -> Unit,
)
