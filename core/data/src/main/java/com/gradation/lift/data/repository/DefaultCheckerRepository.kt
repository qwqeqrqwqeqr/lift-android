package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.network.common.toMessage
import com.gradation.lift.domain.repository.CheckerRepository
import com.gradation.lift.model.user.Email
import com.gradation.lift.model.user.Name
import com.gradation.lift.network.common.DefaultAPIResult
import com.gradation.lift.network.datasource.CheckerDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultCheckerRepository @Inject constructor(
    private val checkerDataSource: CheckerDataSource,
) : CheckerRepository {
    override  fun checkDuplicateEmail(email: Email): Flow<DataState<Boolean>> = flow {
        checkerDataSource.checkDuplicateEmail(email).collect { result ->
            when (result) {
                is DefaultAPIResult.Fail -> emit(DataState.Fail(result.message))
                is DefaultAPIResult.Success -> emit(DataState.Success(result.data))
            }
        }


    }
    override  fun checkDuplicateName(name: Name): Flow<DataState<Boolean>> = flow {
        checkerDataSource.checkDuplicateName(name).collect { result ->
            when (result) {
                is DefaultAPIResult.Fail -> emit(DataState.Fail(result.message))
                is DefaultAPIResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

}