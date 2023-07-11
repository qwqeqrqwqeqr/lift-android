package com.gradation.lift.network.utils


sealed class TestReturnState{
    object Success : TestReturnState()
    object Fail : TestReturnState()
}
