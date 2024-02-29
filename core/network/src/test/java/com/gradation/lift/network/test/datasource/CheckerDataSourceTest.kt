package com.gradation.lift.network.test.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestJsonDataGenerator.Checker.CHECK_DUPLICATE_EMAIL_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Checker.CHECK_DUPLICATE_NAME_RESPONSE_JSON
import com.gradation.lift.network.datasource.checker.CheckerDataSource
import com.gradation.lift.network.datasource.checker.DefaultCheckerDataSource
import com.gradation.lift.network.di.TestDispatcher.testDispatchers
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.CheckerService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
class CheckerDataSourceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var checkerService: CheckerService
    private lateinit var checkerDataSource: CheckerDataSource
    private lateinit var networkResultHandler: NetworkResultHandler

    private val dispatcher: DispatcherProvider = testDispatchers()


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        checkerService = TestServiceModule.testCheckerService(retrofit)
        networkResultHandler =
            NetworkResultHandler(dispatcherProvider = dispatcher)
        checkerDataSource = DefaultCheckerDataSource(
            checkerService,
            networkResultHandler = networkResultHandler, dispatcher
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun checkDuplicateEmailDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(CHECK_DUPLICATE_EMAIL_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(checkerDataSource.checkDuplicateEmail(FAKE_STRING_DATA).first()) {
            Truth.assertThat(
                NetworkResult.Success(
                    FAKE_BOOLEAN_DATA,
                    message = DefaultDataGenerator.FAKE_MESSAGE_DATA
                )
            ).isEqualTo(this)
        }

    }


    @Test
    fun checkDuplicateNameDataSource() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(CHECK_DUPLICATE_NAME_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )


        with(checkerDataSource.checkDuplicateName(FAKE_STRING_DATA).first()) {
            Truth.assertThat(
                NetworkResult.Success(
                    FAKE_BOOLEAN_DATA,
                    message = DefaultDataGenerator.FAKE_MESSAGE_DATA
                )
            ).isEqualTo(this)
        }
    }
}