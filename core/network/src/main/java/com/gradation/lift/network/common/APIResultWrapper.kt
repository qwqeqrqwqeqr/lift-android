package com.gradation.lift.network.common

import kotlinx.serialization.Serializable

/**
 *  [APIResultWrapper]
 *  API 통신 시 응답 결과 형태에 맞게 래핑 해주는 클래스
 *  @property status : 상태 값을 나타냄 성공 시 true 실패 시 false
 *  @property message : 성공 또는 실패에 대한 응답 결과 메시지
 *  @property data : API를 통해 요청된 실제 데이터
 */
@Serializable
data class APIResultWrapper<out T : Any>(
    val status: Boolean,
    val message: String,
    val data: T?,
)
