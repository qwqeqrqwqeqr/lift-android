package com.gradation.lift.domain.usecase.timer

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class InitTimerUseCase @Inject constructor() {
    operator fun invoke(tick: Long = 1000L):Flow<Int>{
        return  (1..Int.MAX_VALUE).asFlow()
            .onEach { delay(tick) }
            .conflate()
            .transform { emit(it) }

    }
}

