package com.gradation.lift.network.fake

import com.gradation.lift.common.dispatcher.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi

class FakeDispatcherProvider  @OptIn(ExperimentalCoroutinesApi::class) constructor(
    override val main: TestDispatcher = UnconfinedTestDispatcher(),
    override val io: TestDispatcher = UnconfinedTestDispatcher(),
    override val default: TestDispatcher = UnconfinedTestDispatcher(),
    override val unconfined: TestDispatcher = UnconfinedTestDispatcher()
) : DispatcherProvider