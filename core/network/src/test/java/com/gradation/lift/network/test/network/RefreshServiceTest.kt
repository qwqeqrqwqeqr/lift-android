package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.fake.TestRetrofit
import com.gradation.lift.network.service.AuthService
import com.gradation.lift.network.service.RefreshService
import com.gradation.lift.network.service.UserService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule


@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest
class RefreshServiceTest {

    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var refreshService: RefreshService
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        refreshService = TestServiceModule.testRefreshService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}