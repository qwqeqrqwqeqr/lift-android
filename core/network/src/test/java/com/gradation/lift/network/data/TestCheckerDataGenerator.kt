package com.gradation.lift.network.data

import com.gradation.lift.network.dto.auth.*
import com.gradation.lift.network.dto.checker.CheckDuplicateEmailResponseDto
import com.gradation.lift.network.dto.checker.CheckDuplicateNameResponseDto
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_STRING_DATA

object TestCheckerDataGenerator {


    internal val checkDuplicateNameResponseDto = CheckDuplicateNameResponseDto(
        result = true
    )

    internal val checkDuplicateEmailResponseDto = CheckDuplicateEmailResponseDto(
        result = true
    )


    val resultResponseJson: String = """
        {
          "status": true,
          "message": "",
          "data": {
            "result": true
          }
        }
    """.trimIndent()
}