package com.gradation.lift.feature.workReady.findWorkCategory.data.event

import com.gradation.lift.model.model.work.WorkPart

/**
 * [FilterEvent]
 * 필터 관련 이벤트
 * [UpdateWorkPartFilter] 운동 부위 필터 업데이트
 * [UpdateSearchText] 검색어 갱신
 * [ClearSearchText] 검색어 삭제
 * @since 2024-01-09 20:34:38
 */
internal sealed interface FilterEvent {

    data class UpdateWorkPartFilter(val workPartFilter: Set<WorkPart>) : FilterEvent
    data class UpdateSearchText(val searchFilterText: String) : FilterEvent
    data object ClearSearchText : FilterEvent
}
