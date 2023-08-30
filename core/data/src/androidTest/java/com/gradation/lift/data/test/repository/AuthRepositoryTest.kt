package com.gradation.lift.data.test.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.fake.datasource.FakeAuthDataSource
import com.gradation.lift.data.fake.oauthmanager.FakeKakaoOauthManager
import com.gradation.lift.data.fake.oauthmanager.FakeNaverOauthManager
import com.gradation.lift.data.repository.DefaultAuthRepository
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ACCESS_TOKEN
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_REFRESH_TOKEN
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.network.datasource.auth.AuthDataSource
import com.gradation.lift.oauth.kakao.KakaoOauthManager
import com.gradation.lift.oauth.naver.NaverOauthManager
import com.gradation.lift.test.rule.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest
class AuthRepositoryTest {


    private lateinit var failDataSource: AuthDataSource
    private lateinit var successDataSource: AuthDataSource
    private lateinit var successRepository: AuthRepository
    private lateinit var failRepository: AuthRepository

    private lateinit var tokenDataStoreDataSource: TokenDataStoreDataSource
    private lateinit var kakaoOauthManager: KakaoOauthManager
    private lateinit var naverOauthManager: NaverOauthManager


    private lateinit var testDataStore: DataStore<Preferences>




    @get:Rule
    var coroutineRule = CoroutineRule()


    @Before
    fun setUp() {
        testDataStore = PreferenceDataStoreFactory.create {
            InstrumentationRegistry.getInstrumentation().targetContext.preferencesDataStoreFile(
                "test_datastore"
            )
        }


        failDataSource = FakeAuthDataSource(TestReturnState.Fail)
        successDataSource = FakeAuthDataSource(TestReturnState.Success)



        tokenDataStoreDataSource = TokenDataStoreDataSource(testDataStore)

        kakaoOauthManager = FakeKakaoOauthManager()
        naverOauthManager = FakeNaverOauthManager()


        successRepository = DefaultAuthRepository(
            successDataSource,
            tokenDataStoreDataSource,
            kakaoOauthManager,
            naverOauthManager
        )
        failRepository = DefaultAuthRepository(
            failDataSource,
            tokenDataStoreDataSource,
            kakaoOauthManager,
            naverOauthManager
        )
    }

    @After
    fun teardown() {
        File(
            InstrumentationRegistry.getInstrumentation().targetContext.filesDir,
            "/"
        ).deleteRecursively()
    }


    @Test
    fun signUpDefault() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.signUpDefault(signUpInfo = ModelDataGenerator.Auth.defaultSignUpInfoModel)
                .first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.signUpDefault(signUpInfo = ModelDataGenerator.Auth.defaultSignUpInfoModel)
                .first()
        )
    }

    @Test
    fun signInDefault() = runTest {


        with(
            successRepository.signInDefault(signInInfo = ModelDataGenerator.Auth.defaultSignInInfoModel)
                .first()
        ) {
            Truth.assertThat(tokenDataStoreDataSource.accessToken.first())
                .isEqualTo(FAKE_ACCESS_TOKEN)
            Truth.assertThat(tokenDataStoreDataSource.refreshToken.first())
                .isEqualTo(FAKE_REFRESH_TOKEN)
            Truth.assertThat(tokenDataStoreDataSource.refreshToken.first())
                .isEqualTo(FAKE_REFRESH_TOKEN)
            Truth.assertThat(tokenDataStoreDataSource.loginMethod.first())
                .isEqualTo(LoginMethod.Common())


            Truth.assertThat(DataState.Success(Unit)).isEqualTo(this)
        }



        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.signInDefault(signInInfo = ModelDataGenerator.Auth.defaultSignInInfoModel)
                .first()
        )


    }


}