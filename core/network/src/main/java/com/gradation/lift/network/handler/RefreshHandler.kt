package com.gradation.lift.network.handler

import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
import com.gradation.lift.network.service.RefreshService
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface RefreshHandler {
    suspend fun refresh()
}

class DefaultRefreshHandler @Inject constructor(
    private val refreshService: RefreshService,
    private val userDataStoreDataSource: UserDataStoreDataSource,
) : RefreshHandler {
    override suspend fun refresh() {
        with(refreshService.refresh(authorization = userDataStoreDataSource.refreshToken.first())){
             if(data?.accessToken == null){
                 userDataStoreDataSource.clearAll()
             }
            else{
                 userDataStoreDataSource.setAccessToken(data.accessToken)
             }
        }
    }
}