package com.gradation.lift.network.datasource

import com.gradation.lift.model.history.History
import com.gradation.lift.network.common.APIResult
import kotlinx.coroutines.flow.Flow

interface PictureDataSource {

    suspend fun getHistory(): Flow<APIResult<List<History>>>

}