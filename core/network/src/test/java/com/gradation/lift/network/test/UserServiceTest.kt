package com.gradation.lift.network.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.service.UserService
import com.squareup.moshi.Moshi
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
class UserServiceTest {




    @Inject
    lateinit var moshi: Moshi
    @Inject
    lateinit var okHttpClient: OkHttpClient
    @Inject
    lateinit var testRetrofit: TestRetrofit

    private lateinit var userService: UserService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        testRetrofit= TestRetrofit(
            mockWebServer= mockWebServer,
            moshi=moshi,
            okHttpClient = okHttpClient)


    }

    @After
    fun teardown() {

    }

    @Test
    fun testSetUp()= runTest{
        val product = userService.getUserDetail()


    }
}
