package com.gradation.lift.domain.usecase.timer

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [InitTimerUseCase]
 * 타이머를 초기화 하고 실행하는 유즈케이스
 * 기본 타이머 딜레이는 1000L로 설정 되어있음
 * @since 2023-08-28 20:21:07
 */
class InitTimerUseCase @Inject constructor() {
    operator fun invoke(tick: Long = 1000L): Flow<Int> {
        return (1..Int.MAX_VALUE).asFlow()
            .onEach { delay(tick) }
            .conflate()
            .transform { emit(it) }

    }
}




