package com.gradation.lift.network.mapper

import com.gradation.lift.model.auth.DefaultSignUpInfo
import com.gradation.lift.network.dto.auth.SignUpDefaultRequestDto


fun DefaultSignUpInfo.toDto() : SignUpDefaultRequestDto=
    SignUpDefaultRequestDto(
        email = this.id,
        password = this.password
    )