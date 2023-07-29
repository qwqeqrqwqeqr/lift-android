package com.gradation.lift.network.mapper

import com.gradation.lift.model.auth.SignUpInfo
import com.gradation.lift.network.dto.auth.SignUpDefaultRequestDto


fun SignUpInfo.toDto() : SignUpDefaultRequestDto=
    SignUpDefaultRequestDto(
        email = this.id,
        password = this.password
    )