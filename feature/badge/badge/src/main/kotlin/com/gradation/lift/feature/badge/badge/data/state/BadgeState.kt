package com.gradation.lift.feature.badge.badge.data.state

import com.gradation.lift.feature.badge.badge.data.model.AllBadge
import com.gradation.lift.feature.badge.badge.data.model.FilterType
import com.gradation.lift.feature.badge.badge.data.model.SortType

/**
 * [BadgeState]
 * 뱃지와 관련한 상태들을 정의한 클래스
 * @property badgeList 화면에서 보여줄 뱃지 리스트 [sortType]과 [filterType]에 따라 각기 다르게 보여줌
 * @property totalBadgeCount 총 뱃지 개수
 * @property acquiredBadgeCount 획득한 뱃지 개수
 * @property unacquiredBadgeCount 미획득한 뱃지 개수
 * @property currentBadgeCount 현재 화면에 보여지는 뱃지 개수 (필터링에 따라 달라짐)
 * @property badgeProgress 획득한 뱃지 달성률
 * @since 2023-09-26 17:15:34
 */
class BadgeState(
    badge: List<AllBadge>,
    private val sortType: SortType,
    private val filterType: FilterType
) {
    val badgeList: List<AllBadge> = when (filterType) {
        is FilterType.Acquired -> {
            when (sortType) {
                is SortType.Name -> badge.filterIsInstance<AllBadge.AcquireBadge>()
                    .sortedBy { it.name }

                is SortType.Newest -> badge.filterIsInstance<AllBadge.AcquireBadge>()
                    .sortedByDescending { it.badgeTimeStamp }

                is SortType.Number -> badge.filterIsInstance<AllBadge.AcquireBadge>()
                    .sortedBy { it.id }

                is SortType.Oldest -> badge.filterIsInstance<AllBadge.AcquireBadge>()
                    .sortedBy { it.badgeTimeStamp }
            }
        }

        is FilterType.All -> {
            when (sortType) {
                is SortType.Name -> badge.sortedBy { it.name }
                is SortType.Newest -> badge.filterIsInstance<AllBadge.AcquireBadge>()
                    .sortedByDescending { it.badgeTimeStamp } + badge.filterIsInstance<AllBadge.UnacquiredBadge>()
                    .sortedBy { it.id }

                is SortType.Number -> badge.sortedBy { it.id }
                is SortType.Oldest -> badge.filterIsInstance<AllBadge.AcquireBadge>()
                    .sortedBy { it.badgeTimeStamp } + badge.filterIsInstance<AllBadge.UnacquiredBadge>()
                    .sortedBy { it.id }
            }
        }

        is FilterType.UnAcquired -> {
            when (sortType) {
                is SortType.Name -> badge.filterIsInstance<AllBadge.UnacquiredBadge>()
                    .sortedBy { it.name }

                is SortType.Newest -> badge.filterIsInstance<AllBadge.UnacquiredBadge>()
                    .sortedBy { it.id }

                is SortType.Number -> badge.filterIsInstance<AllBadge.UnacquiredBadge>()
                    .sortedBy { it.id }

                is SortType.Oldest -> badge.filterIsInstance<AllBadge.UnacquiredBadge>()
                    .sortedBy { it.id }
            }

        }
    }
    val totalBadgeCount = badge.count()
    val acquiredBadgeCount = badge.count { it is AllBadge.AcquireBadge }
    val unacquiredBadgeCount = badge.count { it is AllBadge.UnacquiredBadge }
    val currentBadgeCount = badgeList.count()
    val badgeProgress =
        (badge.count { it is AllBadge.AcquireBadge }.toFloat() / badge.count()
            .toFloat() * 100).toInt()

}