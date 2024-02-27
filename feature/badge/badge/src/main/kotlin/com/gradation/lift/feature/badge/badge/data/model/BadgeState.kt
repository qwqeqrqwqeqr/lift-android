package com.gradation.lift.feature.badge.badge.data.model

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDateTime

/**
 * [BadgeState]
 * 미획득 뱃지와 획득 뱃지로 분류한 사용자 뱃지 클래스
 * @since 2023-09-26 12:54:43
 */
sealed class BadgeState(
    val id: Int,
    val name: String,
    val description: String,
    val hint: String,
    val url: String,
) {
    class AcquireBadge(
        id: Int,
        name: String,
        description: String,
        hint: String,
        url: String,
        val color: Color,
        val backgroundColor: Color,
        val badgeTimeStamp: LocalDateTime,
    ) : BadgeState(id, name, description, hint, url)

    class UnacquiredBadge(
        id: Int,
        name: String,
        description: String,
        hint: String,
        url: String,
    ) : BadgeState(id, name, description, hint, url)
}