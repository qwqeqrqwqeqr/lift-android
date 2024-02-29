package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.CreateUserBadge.CREATE_USER_BADGE_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.CreateUserBadge.CREATE_USER_BADGE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.GetBadge.GET_BADGE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.GetUserBadge.GET_USER_BADGE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.GetUserBadgeByMainFlag.GET_USER_BADGE_BY_MAIN_FLAG_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.GetUserBadgeCondition.GET_USER_BADGE_CONDITION_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.UpdateUserBadgeByMainFlag.UPDATE_USER_BADGE_BY_MAIN_FLAG_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.UpdateUserBadgeByMainFlag.UPDATE_USER_BADGE_BY_MAIN_FLAG_RESPONSE_DTO
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.CREATE_USER_BADGE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.GET_BADGE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.GET_USER_BADGE_BY_MAIN_FLAG_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.GET_USER_BADGE_CONDITION_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.GET_USER_BADGE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.UPDATE_USER_BADGE_BY_MAIN_FLAG_RESPONSE_JSON
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.service.BadgeService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest
class BadgeServiceTest {


    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var badgeService: BadgeService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        badgeService = TestServiceModule.testBadgeService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun getBadgeService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_BADGE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(badgeService.getBadge()) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(GET_BADGE_RESPONSE_DTO)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/badge/badge")
            Truth.assertThat(method).isEqualTo(Constants.GET)
        }
    }

    @Test
    fun getUserBadgeService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_USER_BADGE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(badgeService.getUserBadge()) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(GET_USER_BADGE_RESPONSE_DTO)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/badge/user-badge")
            Truth.assertThat(method).isEqualTo(Constants.GET)
        }
    }

    @Test
    fun createUserBadgeService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(CREATE_USER_BADGE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )


        with(badgeService.createUserBadge(CREATE_USER_BADGE_REQUEST_DTO)) {
            Truth.assertThat(code()).isEqualTo(Constants.CREATED)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(CREATE_USER_BADGE_RESPONSE_DTO)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/badge/user-badge")
            Truth.assertThat(method).isEqualTo(Constants.POST)
        }
    }

    @Test
    fun getUserBadgeByMainFlagService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_USER_BADGE_BY_MAIN_FLAG_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(badgeService.getUserBadgeByMainFlag()) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(GET_USER_BADGE_BY_MAIN_FLAG_RESPONSE_DTO)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/badge/user-badge/main-flag")
            Truth.assertThat(method).isEqualTo(Constants.GET)
        }
    }

    @Test
    fun getUserBadgeByConditionService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_USER_BADGE_CONDITION_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(badgeService.getUserBadgeByCondition()) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(GET_USER_BADGE_CONDITION_RESPONSE_DTO)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/badge/user-badge/condition")
            Truth.assertThat(method).isEqualTo(Constants.GET)
        }
    }


    @Test
    fun updateUserBadgeMainFlagService() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_USER_BADGE_BY_MAIN_FLAG_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(badgeService.updateUserBadgeMainFlag(UPDATE_USER_BADGE_BY_MAIN_FLAG_REQUEST_DTO)) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(UPDATE_USER_BADGE_BY_MAIN_FLAG_RESPONSE_DTO)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/badge/user-badge/main-flag")
            Truth.assertThat(method).isEqualTo(Constants.PATCH)
        }
    }
}