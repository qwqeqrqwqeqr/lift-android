package com.gradation.lift.network.utils

import com.gradation.lift.network.common.APIResult
import javax.annotation.Nullable

sealed class ReturnState{
    object Success : ReturnState()
    object Fail : ReturnState()
    object Error : ReturnState()
    object Loading : ReturnState()
}
