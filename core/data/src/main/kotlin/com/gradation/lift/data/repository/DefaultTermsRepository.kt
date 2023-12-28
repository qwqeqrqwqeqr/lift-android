package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.TermsRepository
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.terms.TermsDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultTermsRepository @Inject constructor(
    private val termsDataSource: TermsDataSource,
) : TermsRepository {

    override fun createUserTermsConsent(
        consent: Boolean,
        marketingConsent: Boolean,
    ): Flow<DataState<Boolean>> = flow {
        termsDataSource.createUserTermsConsent(consent, marketingConsent).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun getUserMarketingTermsConsent(): Flow<DataState<Boolean>> = flow {
        termsDataSource.getUserMarketingTermsConsent().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun updateUserMarketingTermsConsent(marketingConsent: Boolean): Flow<DataState<Boolean>> =
        flow {
            termsDataSource.updateUserMarketingTermsConsent(marketingConsent).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }

}