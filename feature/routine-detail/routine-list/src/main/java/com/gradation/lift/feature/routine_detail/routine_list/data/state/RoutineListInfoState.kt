package com.gradation.lift.feature.routine_detail.routine_list.data.state

/**
 * [RoutineListInfoState]
 * 루틴 리스트의 정보를 가지고 있는 상태
 * @param openedRoutineList 현재 펼쳐진(상세 보기) 루틴들의 목록 (루틴리스트 아이디, 루틴 아이디) 형태
 * @since 2023-11-19 15:21:26
 */
sealed class RoutineListInfoState(
    val openedRoutineList: List<Pair<Int, Int>>,
) {
    class Initial(
        openedRoutineList: List<Pair<Int, Int>> = emptyList()
    ) : RoutineListInfoState(openedRoutineList)

    class Update(
        openedRoutineList: List<Pair<Int, Int>>
    ) : RoutineListInfoState(openedRoutineList)
}


