package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.utils.ModelDataGenerator.Badge.BADGE_CONDITION_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Badge.BADGE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Badge.CREATE_USER_BADGE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Badge.UPDATE_USER_BADGE_MAIN_FLAG_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Badge.USER_BADGE_MODEL
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.CREATE_USER_BADGE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.GET_BADGE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.GET_USER_BADGE_BY_MAIN_FLAG_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.GET_USER_BADGE_CONDITION_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.GET_USER_BADGE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Badge.UPDATE_USER_BADGE_BY_MAIN_FLAG_RESPONSE_JSON
import com.gradation.lift.network.datasource.badge.BadgeRemoteDataSource
import com.gradation.lift.network.datasource.badge.DefaultBadgeRemoteDataSource
import com.gradation.lift.network.di.TestDispatcher
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.BadgeService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
class BadgeRemoteDataSourceTest {
    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var badgeService: BadgeService
    private lateinit var badgeRemoteDataSource: BadgeRemoteDataSource
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
        badgeRemoteDataSource = DefaultBadgeRemoteDataSource(
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
                .setBody(GET_BADGE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(badgeRemoteDataSource.getBadge().first()) {
            Truth.assertThat(
                NetworkResult.Success(listOf(BADGE_MODEL))
            ).isEqualTo(this)
        }
    }

    @Test
    fun getUserBadgeDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_USER_BADGE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(badgeRemoteDataSource.getUserBadge().first()) {
            Truth.assertThat(
                NetworkResult.Success(listOf(USER_BADGE_MODEL))
            ).isEqualTo(this)
        }
    }

    @Test
    fun createUserBadgeDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(CREATE_USER_BADGE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.CREATED)
        )

        with(badgeRemoteDataSource.createUserBadge(CREATE_USER_BADGE_MODEL).first()) {
            Truth.assertThat(
                NetworkResult.Success(Unit)
            ).isEqualTo(this)
        }
    }

    @Test
    fun getUserBadgeByMainFlagDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_USER_BADGE_BY_MAIN_FLAG_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(badgeRemoteDataSource.getUserBadgeByMainFlag().first()) {
            Truth.assertThat(
                NetworkResult.Success(listOf(USER_BADGE_MODEL))
            ).isEqualTo(this)
        }
    }

    @Test
    fun getUserBadgeByConditionDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_USER_BADGE_CONDITION_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(badgeRemoteDataSource.getUserBadgeByCondition().first()) {
            Truth.assertThat(
                NetworkResult.Success(BADGE_CONDITION_MODEL)
            ).isEqualTo(this)
        }
    }


    @Test
    fun updateUserBadgeMainFlagDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_USER_BADGE_BY_MAIN_FLAG_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            badgeRemoteDataSource.updateUserBadgeMainFlag(UPDATE_USER_BADGE_MAIN_FLAG_MODEL).first()
        ) {
            Truth.assertThat(
                NetworkResult.Success(Unit)
            ).isEqualTo(this)
        }
    }

}