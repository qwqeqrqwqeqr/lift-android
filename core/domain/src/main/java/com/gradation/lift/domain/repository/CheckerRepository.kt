package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.user.Email
import com.gradation.lift.model.user.Name
import kotlinx.coroutines.flow.Flow

interface CheckerRepository {

    suspend fun checkDuplicateEmail(email: Email): Flow<DataState<Boolean>>

    suspend fun checkDuplicateName(name: Name): Flow<DataState<Boolean>>
}