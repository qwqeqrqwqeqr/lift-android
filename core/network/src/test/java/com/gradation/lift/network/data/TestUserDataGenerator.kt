package com.gradation.lift.network.data

import com.gradation.lift.network.dto.user.*


internal object TestUserDataGenerator {

    val userDetailDto = UserDetailDto(
        name = "test",
        gender = "male",
        height = 180.0f,
        weight = 83.3f,
        unitOfWeight = "kg"
    )

    val userDetailResponseJson:String = """
        {
          "status": true,
          "message": "",
          "data": {
            "user_detail": {
              "name": "test",
              "gender": "male",
              "height": 180.0,
              "weight": 83.3,
              "unit_of_weight": "kg"
            }
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


    val createUserDetailRequestDto = CreateUserDetailRequestDto(userDetailDto)
    val createUserDetailResponseDto = CreateUserDetailResponseDto(true)
    val existUserDetailResponseDto = ExistUserDetailResponseDto(true)
    val getUserDetailResponseDto = GetUserDetailResponseDto(userDetailDto)
    val updateUserDetailRequestDto = UpdateUserDetailRequestDto(userDetailDto)
    val updateUserDetailResponseDto = UpdateUserDetailResponseDto(true)


}

