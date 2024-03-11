package com.gradation.lift.feature.workReady.findWorkCategory.data.event

import com.gradation.lift.feature.workReady.findWorkCategory.data.event.FilterEvent.ClearSearchText
import com.gradation.lift.feature.workReady.findWorkCategory.data.event.FilterEvent.UpdateFavoriteFilter
import com.gradation.lift.feature.workReady.findWorkCategory.data.event.FilterEvent.UpdatePopularFilter
import com.gradation.lift.feature.workReady.findWorkCategory.data.event.FilterEvent.UpdateRecommendFilter
import com.gradation.lift.feature.workReady.findWorkCategory.data.event.FilterEvent.UpdateSearchText
import com.gradation.lift.feature.workReady.findWorkCategory.data.event.FilterEvent.UpdateWorkPartFilter
import com.gradation.lift.model.model.work.WorkPart

/**
 * [FilterEvent]
 * 필터 관련 이벤트
 * [UpdateWorkPartFilter] 운동 부위 필터 업데이트
 * [UpdateSearchText] 검색어 갱신
 * [ClearSearchText] 검색어 삭제
 * [UpdateFavoriteFilter] 관심 등록 항목만 보여주기 on off
 * [UpdateRecommendFilter] 추천 항목만 보여주기 on off
 * [UpdatePopularFilter] 인기 항목만 보여주기 on off
 * @since 2024-03-10 12:37:29
 */
internal sealed interface FilterEvent {

    data class UpdateWorkPartFilter(val workPartFilter: Set<WorkPart>) : FilterEvent
    data class UpdateSearchText(val searchFilterText: String) : FilterEvent
    data class UpdateFavoriteFilter(val flag: Boolean) : FilterEvent
    data class UpdateRecommendFilter(val flag: Boolean) : FilterEvent
    data class UpdatePopularFilter(val flag: Boolean) : FilterEvent
    data object ClearSearchText : FilterEvent
}
