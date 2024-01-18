package com.gradation.lift.network.datasource.terms

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.dto.terms.CreateUserTermsConsentRequestDto
import com.gradation.lift.network.dto.terms.UpdateUserMarketingTermsConsentRequestDto
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.TermsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DefaultTermsDataSource @Inject constructor(
    private val termsService: TermsService,
    private val networkResultHandler: NetworkResultHandler,
    private val dispatcherProvider: DispatcherProvider,
) : TermsDataSource {

    override suspend fun createUserTermsConsent(
        consent: Boolean,
        marketingConsent: Boolean,
    ): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                termsService.createUserTermsConsent(
                    CreateUserTermsConsentRequestDto(consent, marketingConsent)
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(message = result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override suspend fun getUserMarketingTermsConsent(): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                termsService.getUserMarketingTermsConsent()
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(message = result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }.flowOn(dispatcherProvider.default)


    override suspend fun updateUserMarketingTermsConsent(marketingConsent: Boolean): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                termsService.updateUserMarketingTermsConsent(
                    UpdateUserMarketingTermsConsentRequestDto(marketingConsent)
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(message = result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }.flowOn(dispatcherProvider.default)


}

