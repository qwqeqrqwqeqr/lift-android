package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.user.Email
import com.gradation.lift.model.user.Name
import kotlinx.coroutines.flow.Flow

interface CheckerRepository {

    fun checkDuplicateEmail(email: Email): Flow<DataState<Boolean>>

    fun checkDuplicateName(name: Name): Flow<DataState<Boolean>>
}