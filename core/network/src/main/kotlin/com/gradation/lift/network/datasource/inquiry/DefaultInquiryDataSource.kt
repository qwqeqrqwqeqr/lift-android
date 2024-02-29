package com.gradation.lift.network.datasource.inquiry

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.dto.inquiry.CreateInquiryRequestDto
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.InquiryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DefaultInquiryDataSource @Inject constructor(
    private val inquiryService: InquiryService,
    private val networkResultHandler: NetworkResultHandler,
    private val dispatcherProvider: DispatcherProvider,
) : InquiryDataSource {


    override suspend fun createInquiry(content: String): Flow<NetworkResult<Unit>> = flow {
        networkResultHandler {
            inquiryService.createInquiry(
                CreateInquiryRequestDto(content = content)
            )
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(message = result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
            }
        }
    }.flowOn(dispatcherProvider.default)


}

