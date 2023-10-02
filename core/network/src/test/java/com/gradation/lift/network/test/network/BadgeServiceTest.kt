package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.createUserBadgeRequestDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.createUserBadgeResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.getBadgeResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.getUserBadgeByMainFlagResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.getUserBadgeConditionResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.getUserBadgeResponseDto
import com.gradation.lift.network.data.TestJsonDataGenerator
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
                .setBody(TestJsonDataGenerator.Badge.badgeResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(badgeService.getBadge()) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(getBadgeResponseDto)
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
                .setBody(TestJsonDataGenerator.Badge.userBadgeResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(badgeService.getUserBadge()) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(getUserBadgeResponseDto)
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
                .setBody(TestJsonDataGenerator.Common.resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )


        with(badgeService.createUserBadge(createUserBadgeRequestDto)) {
            Truth.assertThat(code()).isEqualTo(Constants.CREATED)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(createUserBadgeResponseDto)
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
                .setBody(TestJsonDataGenerator.Badge.userBadgeResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(badgeService.getUserBadgeByMainFlag()) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(getUserBadgeByMainFlagResponseDto)
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
                .setBody(TestJsonDataGenerator.Badge.badgeConditionResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(badgeService.getUserBadgeByCondition()) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(getUserBadgeConditionResponseDto)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/badge/user-badge/condition")
            Truth.assertThat(method).isEqualTo(Constants.GET)
        }
    }
}