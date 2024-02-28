package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Terms.CreateUserTermsConsent.CREATE_USER_TERMS_CONSENT_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Terms.CreateUserTermsConsent.CREATE_USER_TERMS_CONSENT_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Terms.GetUserMarketingTermsConsent.GET_USER_MARKETING_TERMS_CONSENT_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Terms.UpdateUserMarketingTermsConsent.UPDATE_USER_MARKETING_TERMS_CONSENT_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Terms.UpdateUserMarketingTermsConsent.UPDATE_USER_MARKETING_TERMS_CONSENT_RESPONSE_DTO
import com.gradation.lift.network.data.TestJsonDataGenerator.Terms.CREATE_USER_TERMS_CONSENT_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Terms.GET_USER_MARKETING_TERMS_CONSENT_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Terms.UPDATE_USER_MARKETING_TERMS_CONSENT_RESPONSE_JSON
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.service.TermsService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
class TermsServiceTest {


    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var termsService: TermsService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        termsService = TestServiceModule.testTermsService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun createUserTermsConsentService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(CREATE_USER_TERMS_CONSENT_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(termsService.createUserTermsConsent(CREATE_USER_TERMS_CONSENT_REQUEST_DTO)) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(CREATE_USER_TERMS_CONSENT_RESPONSE_DTO)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/terms/consent/terms")
            Truth.assertThat(method).isEqualTo(Constants.POST)
        }
    }

    @Test
    fun getUserMarketingTermsConsentService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_USER_MARKETING_TERMS_CONSENT_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(termsService.getUserMarketingTermsConsent()) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(GET_USER_MARKETING_TERMS_CONSENT_RESPONSE_DTO)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/terms/consent/marketing-terms")
            Truth.assertThat(method).isEqualTo(Constants.GET)
        }
    }

    @Test
    fun updateUserMarketingTermsConsentService() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_USER_MARKETING_TERMS_CONSENT_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(
            termsService.updateUserMarketingTermsConsent(
                UPDATE_USER_MARKETING_TERMS_CONSENT_REQUEST_DTO
            )
        ) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(UPDATE_USER_MARKETING_TERMS_CONSENT_RESPONSE_DTO)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/terms/consent/marketing-terms")
            Truth.assertThat(method).isEqualTo(Constants.PATCH)
        }
    }
}