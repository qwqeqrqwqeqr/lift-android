package com.gradation.lift.designsystem.component.chart.state

import com.gradation.lift.designsystem.component.chart.model.WorkCategoryCount

/**
 * [PieChartState]
 *  @param workPartList 운동 부위 리스트
 *  @param selectedWorkPart 현재 선택된 운동부위
 *  @param workCategoryCountByWorkPartList 선택된 운동 부위에 따른 운동 카테고리로 4개만 설정
 *  @param sumOfWorkCategoryCountByWorkPart 선택된 운동 부위에 따른 운동 카테고리 운동횟수 총합
 *  @param updateSelectedWorkPart 운동 부위 변경
 *  @since 2024-02-23 09:42:45
 */
data class PieChartState(
    val workPartList: List<String>,
    val selectedWorkPart: String,
    val workCategoryCountByWorkPartList: List<WorkCategoryCount>,
    val sumOfWorkCategoryCountByWorkPart: Int,
    val selectedDateTotalWorkCount: Int,
    val updateSelectedWorkPart: (String) -> Unit,
)
