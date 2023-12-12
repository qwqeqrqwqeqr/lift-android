package com.gradation.lift.feature.badge.badge.data.model

import kotlinx.datetime.LocalDateTime

/**
 * [AllBadge]
 * 미획득 뱃지와 획득 뱃지로 분류한 뱃지 클래스
 * @since 2023-09-26 12:54:43
 */
sealed class AllBadge(
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
        val badgeTimeStamp: LocalDateTime,
    ) : AllBadge(id, name, description, hint, url)

    class UnacquiredBadge(
        id: Int,
        name: String,
        description: String,
        hint: String,
        url: String
    ) : AllBadge(id, name, description, hint, url)
}