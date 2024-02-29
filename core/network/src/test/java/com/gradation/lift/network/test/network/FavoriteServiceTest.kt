package com.gradation.lift.network.test.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.data.TestDtoDataGenerator.Favorite.GetWorkCategoryFavorite.GET_WORK_CATEGORY_FAVORITE_RESPONSE_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Favorite.UpdateWorkCategoryFavorite.UPDATE_WORK_CATEGORY_FAVORITE_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Favorite.UpdateWorkCategoryFavorite.UPDATE_WORK_CATEGORY_FAVORITE_RESPONSE_DTO
import com.gradation.lift.network.data.TestJsonDataGenerator.Favorite.GET_WORK_CATEGORY_FAVORITE_RESPONSE_JSON
import com.gradation.lift.network.data.TestJsonDataGenerator.Favorite.UPDATE_WORK_CATEGORY_FAVORITE_RESPONSE_JSON
import com.gradation.lift.network.di.TestRetrofit
import com.gradation.lift.network.di.TestServiceModule
import com.gradation.lift.network.service.FavoriteService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
class FavoriteServiceTest {


    private lateinit var retrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer
    private lateinit var favoriteService: FavoriteService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        retrofit = TestRetrofit(mockWebServer = mockWebServer)
        favoriteService = TestServiceModule.testFavoriteService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun getWorkCategoryFavorite() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(GET_WORK_CATEGORY_FAVORITE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(favoriteService.getWorkCategoryFavorite()) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(GET_WORK_CATEGORY_FAVORITE_RESPONSE_DTO)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/favorite/work-category")
            Truth.assertThat(method).isEqualTo(Constants.GET)
        }
    }

    @Test
    fun updateWorkCategoryFavorite() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(UPDATE_WORK_CATEGORY_FAVORITE_RESPONSE_JSON)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(Constants.OK)
        )

        with(favoriteService.updateWorkCategoryFavorite(UPDATE_WORK_CATEGORY_FAVORITE_REQUEST_DTO)) {
            Truth.assertThat(code()).isEqualTo(Constants.OK)
            Truth.assertThat(body()).isInstanceOf(APIResultWrapper::class.java)
            Truth.assertThat(body()!!.data)
                .isEqualTo(UPDATE_WORK_CATEGORY_FAVORITE_RESPONSE_DTO)
        }

        with(mockWebServer.takeRequest()) {
            Truth.assertThat(path).isEqualTo("/favorite/work-category")
            Truth.assertThat(method).isEqualTo(Constants.POST)
        }
    }


}