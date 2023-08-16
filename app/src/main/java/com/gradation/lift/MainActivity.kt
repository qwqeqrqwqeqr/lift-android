package com.gradation.lift

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.domain.usecase.auth.SignInKakaoUseCase
import com.gradation.lift.domain.usecase.auth.SignInNaverUseCase
import com.gradation.lift.oauth.common.naverInitializer
import com.gradation.lift.oauth.state.OAuthSignInState
import com.gradation.lift.state.SplashUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()


    @Inject
    lateinit var signInNaverUseCase: SignInNaverUseCase

    @Inject
    lateinit var signInKakaoUseCase: SignInKakaoUseCase

    @Inject
    lateinit var dispatcherProvider: DispatcherProvider


    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        naverInitializer(
            this@MainActivity,
            BuildConfig.NAVER_OAUTH_CLIENT_ID,
            BuildConfig.NAVER_OAUTH_CLIENT_SECRET,
            BuildConfig.NAVER_OAUTH_CLIENT_NAME
        )


        var splashUiState: SplashUiState by mutableStateOf(SplashUiState.Loading)
        var naverOAuthSignInState: OAuthSignInState by mutableStateOf(OAuthSignInState.None)
        var kakaoOauthSignInState: OAuthSignInState by mutableStateOf(OAuthSignInState.None)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.splashUiState.onEach { splashUiState = it }.collect()
            }
        }

        fun signInNaver() {
            CoroutineScope(dispatcherProvider.default).launch {
                signInNaverUseCase().collect { naverOAuthSignInState = it }
            }
        }

        fun signInKakao() {
            CoroutineScope(dispatcherProvider.default).launch {
                signInKakaoUseCase().collect { kakaoOauthSignInState = it }
            }
        }






        installSplashScreen().apply {
            setKeepOnScreenCondition {
                when (splashUiState) {
                    SplashUiState.Loading -> false
                    SplashUiState.Login -> true
                    SplashUiState.Main -> true
                    SplashUiState.RegisterDetail -> true
                }
            }
        }




        setContent {
            val systemUiController = rememberSystemUiController()
            viewModel.setDefaultSystemUiController(systemUiController)
            LiftMaterialTheme()
            {
                LiftApp(
                    splashUiState = splashUiState,
                    windowSizeClass = calculateWindowSizeClass(this),
                    naverOAuthSignInState = naverOAuthSignInState,
                    kakaoOauthSignInState = kakaoOauthSignInState,
                    signInNaver = { signInNaver() },
                    signInKakao = { signInKakao() }
                )
            }
        }


    }
}





