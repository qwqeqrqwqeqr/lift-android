package com.gradation.lift.data.utils


sealed class TestReturnState{
    object Success : TestReturnState()
    object Fail : TestReturnState()
}
