package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.datastore.datasource.DataStoreDataSource
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.auth.Account
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.datasource.AuthDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val dataStoreDataSource: DataStoreDataSource,
): AuthRepository {
    override fun signIn(account: Account): Flow<DataState<Token>> {
        TODO("Not yet implemented")
    }
}