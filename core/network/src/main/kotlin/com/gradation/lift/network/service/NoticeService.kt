package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.notice.GetNoticeByIdResponseDto
import com.gradation.lift.network.dto.notice.GetNoticeResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * [NoticeService]
 * 공지사항 관련 서비스
 * @since 2023-09-21 14:20:42
 */
interface NoticeService {


    /**
     * [getNotice]
     * 공지사항 불러오기
     * @since 2024-01-11 17:30:09
     */
    @GET("notice/notice")
    suspend fun getNotice(): Response<APIResultWrapper<GetNoticeResponseDto>>

    /**
     * [getNoticeById]
     * 공지사항 아이디를 통해 공지사항 불러오기
     * @since 2024-01-11 17:30:09
     */
    @GET("notice/notice-by-id")
    suspend fun getNoticeById(@Query("notice_id") noticeId: Int): Response<APIResultWrapper<GetNoticeByIdResponseDto>>
}