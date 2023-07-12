package com.gradation.lift.network.data

import com.gradation.lift.network.dto.auth.RefreshResponseDto

object TestRefreshDataGenerator {

    internal const val TEST_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODkxNDk1NTksInN1YiI6IjIwMTcxMzcyMSJ9.hW0xW24LoBbGKvftst-reNFdqxc2P89Q5sxJuSJOvGI"
    internal const val TEST_REFRESH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTE3MzQzNTksInN1YiI6IjIwMTcxMzcyMSJ9.CGSYkn3ilfDxD0J7P_XeVO1EA3k7g_tHEh6MHXAPEpk"

    internal val refreshResponseDto = RefreshResponseDto(
        accessToken = TEST_ACCESS_TOKEN
    )

    internal val refreshResponseJson ="""
        {
          "status": true,
          "message": "",
          "data": {
            "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODkxNDk1NTksInN1YiI6IjIwMTcxMzcyMSJ9.hW0xW24LoBbGKvftst-reNFdqxc2P89Q5sxJuSJOvGI"
          }
        }
    """.trimIndent()
}