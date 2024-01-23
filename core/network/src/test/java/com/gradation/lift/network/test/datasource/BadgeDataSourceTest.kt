package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.utils.ModelDataGenerator.Badge.badgeConditionModel
import com.gradation.lift.model.utils.ModelDataGenerator.Badge.badgeModel
import com.gradation.lift.model.utils.ModelDataGenerator.Badge.createUserBadgeModel
import com.gradation.lift.model.utils.ModelDataGenerator.Badge.userBadgeModel
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.badgeConditionResponseJson
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.badgeResponseJson
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.userBadgeResponseJson
import com.gradation.lift.network.data.TestJsonDataGenerator.Common.resultResponseJson
import com.gradation.lift.network.datasource.badge.BadgeDataSource
import com.gradation.lift.network.datasource.badge.DefaultBadgeDataSource
import com.gradation.lift.network.di.TestDispatcher
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.BadgeService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest
class BadgeDataSourceTest {
    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var badgeService: BadgeService
    private lateinit var badgeDataSource: BadgeDataSource
    private lateinit var networkResultHandler: NetworkResultHandler

    private val dispatcher: DispatcherProvider = TestDispatcher.testDispatchers()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        badgeService = TestServiceModule.testBadgeService(retrofit)
        networkResultHandler =
            NetworkResultHandler(dispatcherProvider = dispatcher)
        badgeDataSource = DefaultBadgeDataSource(
            badgeService, networkResultHandler, dispatcher
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getBadgeDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(badgeResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(badgeDataSource.getBadge().first()) {
            Truth.assertThat(
                NetworkResult.Success(listOf(badgeModel))
            ).isEqualTo(this)
        }
    }

    @Test
    fun getUserBadgeDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(userBadgeResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(badgeDataSource.getUserBadge().first()) {
            Truth.assertThat(
                NetworkResult.Success(listOf(userBadgeModel))
            ).isEqualTo(this)
        }
    }

    @Test
    fun createUserBadgeDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(resultResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )

        with(badgeDataSource.createUserBadge(createUserBadgeModel).first()) {
            Truth.assertThat(
                NetworkResult.Success(Unit)
            ).isEqualTo(this)
        }
    }

    @Test
    fun getUserBadgeByMainFlagDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(userBadgeResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(badgeDataSource.getUserBadgeByMainFlag().first()) {
            Truth.assertThat(
                NetworkResult.Success(listOf(userBadgeModel))
            ).isEqualTo(this)
        }
    }

    @Test
    fun getUserBadgeByConditionDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(badgeConditionResponseJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(badgeDataSource.getUserBadgeByCondition().first()) {
            Truth.assertThat(
                NetworkResult.Success(badgeConditionModel)
            ).isEqualTo(this)
        }
    }


}