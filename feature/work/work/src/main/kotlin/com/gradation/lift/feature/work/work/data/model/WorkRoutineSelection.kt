package com.gradation.lift.feature.work.work.data.model

import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart

/**
 * [WorkRoutineSelection]
 * 현재 운동의 루틴 정보를 담고 있는 모델
 * @property index 현재 루틴의 순번
 * @property workCategory 해당 루틴의 카테고리
 * @property opened 해당 루틴의 상세정보가 열려져 있는지 여부 (운동 조회 화면에서 열기 접기에 반영)
 * @property workSetList 해당 루틴의 세트 횟수 목록
 * @since 2023-08-22 16:26:21
 */
data class WorkRoutineSelection(
    val index: Int= 0,
    val workCategory: WorkCategory = WorkCategory(0,"", WorkPart(0,""),"",""),
    var opened: Boolean = false,
    val workSetList: List<WorkSetSelection> = listOf(),
)