package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.flow.Flow

/**
 * [CheckerRepository]
 * 유효성을 검증하는 저장소
 * @since 2023-08-28 17:01:32
 */
interface CheckerRepository {

    /**
     * [checkDuplicateEmail]
     *  입력 받은 이메일과 동일한 이메일이 존재하는지 확인
     *  @since 2023-08-28 17:02:16
     */
    fun checkDuplicateEmail(email: String): Flow<DataState<Boolean>>

    /**
     * [checkDuplicateName]
     * 입력 받은 이름(닉네임)과 동일한 이름(닉네임)이 존재하는지 확인
     * @since 2023-08-28 17:02:47
     */
    fun checkDuplicateName(name: String): Flow<DataState<Boolean>>
}