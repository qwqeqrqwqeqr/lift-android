package com.gradation.lift.feature.createRoutine.findWorkCategory.data.event

import com.gradation.lift.model.model.work.WorkPart

/**
 * [FilterEvent]
 * 필터 관련 이벤트
 * [UpdateWorkPartFilter] 운동 부위 필터 업데이트
 * [UpdateSearchText] 검색어 갱신
 * @since 2023-12-08 10:14:21
 */
internal sealed interface FilterEvent {

    data class UpdateWorkPartFilter(val workPartFilter: Set<WorkPart>) : FilterEvent
    data class UpdateSearchText(val searchFilterText: String) : FilterEvent

}
