package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.user.DeleteUserInfo
import com.gradation.lift.network.dto.user.DeleteUserRequestDto


fun DeleteUserInfo.toDto() = DeleteUserRequestDto(reason)