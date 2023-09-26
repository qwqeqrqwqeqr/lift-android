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
 * @property badgeProgress 획득한 뱃지 달성률
 * @since 2023-09-26 12:58:28
 */
class BadgeState(
    badge: List<AllBadge>,
    private val sortType: SortType,
    private val filterType: FilterType
) {
    val badgeList: List<AllBadge> = when (filterType) {
        FilterType.Acquired -> {
            when (sortType) {
                SortType.Name -> badge.filterIsInstance<AllBadge.AcquireBadge>()
                    .sortedBy { it.name }

                SortType.Newest -> badge.filterIsInstance<AllBadge.AcquireBadge>()
                    .sortedByDescending { it.badgeTimeStamp }

                SortType.Number -> badge.filterIsInstance<AllBadge.AcquireBadge>()
                    .sortedBy { it.id }

                SortType.Oldest -> badge.filterIsInstance<AllBadge.AcquireBadge>()
                    .sortedBy { it.badgeTimeStamp }
            }
        }

        FilterType.All -> {
            when (sortType) {
                SortType.Name -> badge.sortedBy { it.name }
                SortType.Newest -> badge.filterIsInstance<AllBadge.AcquireBadge>()
                    .sortedByDescending { it.badgeTimeStamp } + badge.filterIsInstance<AllBadge.UnacquiredBadge>()
                    .sortedBy { it.id }

                SortType.Number -> badge.sortedBy { it.id }
                SortType.Oldest -> badge.filterIsInstance<AllBadge.AcquireBadge>()
                    .sortedBy { it.badgeTimeStamp } + badge.filterIsInstance<AllBadge.UnacquiredBadge>()
                    .sortedBy { it.id }
            }
        }

        FilterType.UnAcquired -> {
            when (sortType) {
                SortType.Name -> badge.filterIsInstance<AllBadge.UnacquiredBadge>()
                    .sortedBy { it.name }

                SortType.Newest -> badge.filterIsInstance<AllBadge.UnacquiredBadge>()
                    .sortedBy { it.id }

                SortType.Number -> badge.filterIsInstance<AllBadge.UnacquiredBadge>()
                    .sortedBy { it.id }

                SortType.Oldest -> badge.filterIsInstance<AllBadge.UnacquiredBadge>()
                    .sortedBy { it.id }
            }

        }
    }

    val totalBadgeCount = badge.count()
    val acquiredBadgeCount = badge.count { it is AllBadge.AcquireBadge }
    val unacquiredBadgeCount = badge.count { it is AllBadge.UnacquiredBadge }
    val badgeProgress =
        (badge.count { it is AllBadge.AcquireBadge }.toFloat() / badge.count()
            .toFloat() * 100).toInt()

}