package com.gradation.lift.feature.routineDetail.routineList.data.model


/**
 * [Name] 가나다 순 조회
 * [Count] 많이 사용한 운동횟수 순 조회
 * @since 2023-11-18 17:22:32
 */
internal sealed interface SortType {
    data object Name : SortType
    data object Count : SortType


    fun getName(): String = when (val type = this) {
        Count -> "사용순"
        Name -> "기본(이름순)"
    }
}
