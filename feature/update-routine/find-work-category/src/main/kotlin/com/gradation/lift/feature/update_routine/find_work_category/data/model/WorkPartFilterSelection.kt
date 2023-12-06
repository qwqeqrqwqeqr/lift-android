package com.gradation.lift.feature.update_routine.find_work_category.data.model


/**
 * [WorkPartFilterSelection]
 * 사용자가 현재 어떤 값을 필터링 했는지 확인하기 위해 [selected] 필드가 들어감
 * @property workPart 운동 부위
 * @property selected 해당 부위가 선택(필터링) 되었는지 여부
 * @since 2023-09-13 17:46:08
 */
data class WorkPartFilterSelection(
    val workPart: String,
    val selected: Boolean = false,
){}