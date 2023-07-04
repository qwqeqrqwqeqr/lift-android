package com.gradation.lift.network.datasource

import com.gradation.lift.model.user.Email
import com.gradation.lift.model.user.Name
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.AuthAPIResult
import com.gradation.lift.network.common.DefaultAPIResult
import kotlinx.coroutines.flow.Flow

interface CheckerDataSource {
    suspend fun checkDuplicateEmail(email: Email): Flow<DefaultAPIResult<Boolean>>

    suspend fun checkDuplicateName(name: Name): Flow<DefaultAPIResult<Boolean>>
}