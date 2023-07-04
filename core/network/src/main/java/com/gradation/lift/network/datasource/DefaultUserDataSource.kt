package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.SignInInfo
import com.gradation.lift.model.user.Email
import com.gradation.lift.model.auth.SignUpInfo
import com.gradation.lift.model.auth.Token
import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.user.UnitOfWeight
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.network.common.AuthAPIResult
import com.gradation.lift.network.common.DefaultAPIResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.dto.auth.SignInDefaultRequestDto
import com.gradation.lift.network.dto.auth.SignUpDefaultRequestDto
import com.gradation.lift.network.dto.user.CreateUserDetailRequestDto
import com.gradation.lift.network.dto.user.UpdateUserDetailRequestDto
import com.gradation.lift.network.dto.user.UserDetailDto
import com.gradation.lift.network.service.AuthService
import com.gradation.lift.network.service.UserService
import com.squareup.moshi.Json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultUserDataSource @Inject constructor(
    private val userService: UserService,
    private val networkResultHandler: NetworkResultHandler,
) : UserDataSource {

    override suspend fun getUserDetail(token: Token): Flow<AuthAPIResult<UserDetail>> {
        TODO("Not yet implemented")
    }

    override suspend fun createUserDetail(
        token: Token,
        userDetail: UserDetail,
    ): Flow<AuthAPIResult<Boolean>> = flow {
        networkResultHandler.executeAuth {
            userService.createUserDetail(
                accessToken = token.accessToken,
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
                is AuthAPIResult.Fail -> emit(AuthAPIResult.Fail(result.message))
                is AuthAPIResult.Error -> emit(AuthAPIResult.Error(result.throwable))
                is AuthAPIResult.Success -> emit(AuthAPIResult.Success(result.data.result))
                AuthAPIResult.Refresh -> emit(AuthAPIResult.Refresh)
            }
        }
    }

    override suspend fun updateUserDetail(
        token: Token,
        userDetail: UserDetail,
    ): Flow<AuthAPIResult<Boolean>> = flow {
        networkResultHandler.executeAuth {
            userService.updateUserDetail(
                accessToken = token.accessToken,
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
                is AuthAPIResult.Fail -> emit(AuthAPIResult.Fail(result.message))
                is AuthAPIResult.Error -> emit(AuthAPIResult.Error(result.throwable))
                is AuthAPIResult.Success -> emit(AuthAPIResult.Success(result.data.result))
                AuthAPIResult.Refresh ->  emit(AuthAPIResult.Refresh)
            }
        }
    }

    override suspend fun existUserDetail(token: Token): Flow<AuthAPIResult<Boolean>> = flow {
        networkResultHandler.executeAuth {
            userService.existUserDetail(accessToken = token.accessToken)
        }.collect { result ->
            when (result) {
                is AuthAPIResult.Fail -> emit(AuthAPIResult.Fail(result.message))
                is AuthAPIResult.Error -> emit(AuthAPIResult.Error(result.throwable))
                is AuthAPIResult.Success -> emit(AuthAPIResult.Success(result.data.result))
                AuthAPIResult.Refresh ->  emit(AuthAPIResult.Refresh)
            }
        }
    }

}
