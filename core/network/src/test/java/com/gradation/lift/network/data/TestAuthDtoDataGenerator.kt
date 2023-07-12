package com.gradation.lift.network.data

import com.gradation.lift.network.dto.auth.*
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_STRING_DATA

object TestAuthDtoDataGenerator {

    internal const val TEST_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODkxNDk1NTksInN1YiI6IjIwMTcxMzcyMSJ9.hW0xW24LoBbGKvftst-reNFdqxc2P89Q5sxJuSJOvGI"
    internal const val TEST_REFRESH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTE3MzQzNTksInN1YiI6IjIwMTcxMzcyMSJ9.CGSYkn3ilfDxD0J7P_XeVO1EA3k7g_tHEh6MHXAPEpk"



    internal val signInDefaultResponseDto = SignInDefaultResponseDto(
        accessToken = TEST_ACCESS_TOKEN,
        refreshToken = TEST_REFRESH_TOKEN
    )

    internal val signInDefaultRequestDto = SignInDefaultRequestDto(
        id = FAKE_STRING_DATA,
        password = FAKE_STRING_DATA
    )

    internal val signUpDefaultRequestDto = SignUpDefaultRequestDto(
        email = FAKE_STRING_DATA,
        password = FAKE_STRING_DATA,
        name = FAKE_STRING_DATA,
        phoneNumber = FAKE_STRING_DATA
    )
    internal val signUpDefaultResponseDto = SignUpDefaultResponseDto(
        result = true
    )

    internal val signInDefaultResponseJson ="""
        {
          "status": true,
          "message": "",
          "data": {
            "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODkxNDk1NTksInN1YiI6IjIwMTcxMzcyMSJ9.hW0xW24LoBbGKvftst-reNFdqxc2P89Q5sxJuSJOvGI",
            "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTE3MzQzNTksInN1YiI6IjIwMTcxMzcyMSJ9.CGSYkn3ilfDxD0J7P_XeVO1EA3k7g_tHEh6MHXAPEpk"
          }
        }
    """.trimIndent()

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