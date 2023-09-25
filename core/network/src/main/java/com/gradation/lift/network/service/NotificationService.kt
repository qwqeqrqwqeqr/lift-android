package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.badge.GetBadgeResponseDto
import com.gradation.lift.network.dto.notification.GetNoticeResponseDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * [NotificationService]
 * 공지사항 확인 및 푸시 메시지 알림과 같은 알림 관련 서비스
 * [getNotice] 모든 공지사항을 불러옵니다.
 * @since 2023-09-21 14:20:42
 */
interface NotificationService {

    @GET("notification/notice")
    suspend fun getNotice(): Response<APIResultWrapper<GetNoticeResponseDto>>

}