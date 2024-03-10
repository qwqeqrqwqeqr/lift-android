package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.TermsRepository
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.terms.TermsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultTermsRepository @Inject constructor(
    private val termsRemoteDataSource: TermsRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : TermsRepository {

    override fun createUserTermsConsent(
        consent: Boolean,
        marketingConsent: Boolean,
    ): Flow<DataState<Boolean>> = flow {
        termsRemoteDataSource.createUserTermsConsent(consent, marketingConsent).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getUserMarketingTermsConsent(): Flow<DataState<Boolean>> = flow {
        termsRemoteDataSource.getUserMarketingTermsConsent().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun updateUserMarketingTermsConsent(marketingConsent: Boolean): Flow<DataState<Boolean>> =
        flow {
            termsRemoteDataSource.updateUserMarketingTermsConsent(marketingConsent)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                        is NetworkResult.Success -> emit(DataState.Success(result.data))
                    }
                }
        }.flowOn(dispatcherProvider.default)

}