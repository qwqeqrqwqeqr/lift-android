package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.Token
import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.common.UnitOfWeight
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.dto.user.CreateUserDetailRequestDto
import com.gradation.lift.network.dto.user.UpdateUserDetailRequestDto
import com.gradation.lift.network.dto.user.UserDetailDto
import com.gradation.lift.network.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultUserDataSource @Inject constructor(
    private val userService: UserService,
    private val networkResultHandler: NetworkResultHandler,
) : UserDataSource {

    override suspend fun getUserDetail(token: Token): Flow<APIResult<UserDetail>> = flow {
        networkResultHandler {
            userService.getUserDetail()
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))
                is APIResult.Success -> emit(APIResult.Success(result.data.toUserDetail()))
            }
        }
    }

    override suspend fun createUserDetail(
        token: Token,
        userDetail: UserDetail,
    ): Flow<APIResult<Boolean>> = flow {
        networkResultHandler {
            userService.createUserDetail(
                createUserDetailRequestDto = CreateUserDetailRequestDto(
                    userDetailDto = UserDetailDto(
                        name = userDetail.name,
                        gender = when (val gender = userDetail.gender) {
                            is Gender.FEMALE -> gender.value
                            is Gender.MALE -> gender.value
                        },
                        height = userDetail.height,
                        weight = userDetail.weight,
                        unitOfWeight = when (val unitOfWeight = userDetail.unitOfWeight) {
                            is UnitOfWeight.KG -> unitOfWeight.value
                            is UnitOfWeight.LB -> unitOfWeight.value
                        }
                    )
                )
            )
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))
                is APIResult.Success -> emit(APIResult.Success(result.data.result))
            }
        }
    }

    override suspend fun updateUserDetail(
        token: Token,
        userDetail: UserDetail,
    ): Flow<APIResult<Boolean>> = flow {
        networkResultHandler {
            userService.updateUserDetail(
                updateUserDetailRequestDto = UpdateUserDetailRequestDto(
                    userDetailDto = UserDetailDto(
                        name = userDetail.name,
                        gender = when (val gender = userDetail.gender) {
                            is Gender.FEMALE -> gender.value
                            is Gender.MALE -> gender.value
                        },
                        height = userDetail.height,
                        weight = userDetail.weight,
                        unitOfWeight = when (val unitOfWeight = userDetail.unitOfWeight) {
                            is UnitOfWeight.KG -> unitOfWeight.value
                            is UnitOfWeight.LB -> unitOfWeight.value
                        }
                    )
                )
            )
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))
                is APIResult.Success -> emit(APIResult.Success(result.data.result))
            }
        }
    }

    override suspend fun existUserDetail(token: Token): Flow<APIResult<Boolean>> = flow {
        networkResultHandler {
            userService.existUserDetail()
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))
                is APIResult.Success -> emit(APIResult.Success(result.data.result))
            }
        }
    }

}

