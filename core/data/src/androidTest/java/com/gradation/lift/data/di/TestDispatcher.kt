package com.gradation.lift.data.di

import com.gradation.lift.common.common.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object TestDispatcher {

    fun testDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Unconfined
        override val io: CoroutineDispatcher
            get() = Dispatchers.Unconfined
        override val default: CoroutineDispatcher
            get() = Dispatchers.Unconfined
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}