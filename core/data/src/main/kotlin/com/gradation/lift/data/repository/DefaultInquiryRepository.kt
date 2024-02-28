package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.InquiryRepository
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.inquiry.InquiryDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultInquiryRepository @Inject constructor(
    private val inquiryDataSource: InquiryDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : InquiryRepository {
    override fun createInquiry(content: String): Flow<DataState<Unit>> = flow {
        inquiryDataSource.createInquiry(content).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)


}