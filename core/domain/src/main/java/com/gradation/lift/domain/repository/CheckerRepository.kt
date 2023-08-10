package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.flow.Flow

interface CheckerRepository {

    fun checkDuplicateEmail(email: String): Flow<DataState<Boolean>>

    fun checkDuplicateName(name: String): Flow<DataState<Boolean>>
}