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
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.domain.usecase.auth.ConnectOAuthFromKakaoUseCase
import com.gradation.lift.domain.usecase.auth.ConnectOAuthFromNaverUseCase
import com.gradation.lift.oauth.common.naverInitializer
import com.gradation.lift.state.SplashState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect
import rememberAppState
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()


    @Inject
    lateinit var connectOAuthFromNaverUseCase: ConnectOAuthFromNaverUseCase

    @Inject
    lateinit var connectOAuthFromKakaoUseCase: ConnectOAuthFromKakaoUseCase

    @Inject
    lateinit var dispatcherProvider: DispatcherProvider

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var splashState: SplashState by mutableStateOf(SplashState.Loading)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                when (splashState) {
                    SplashState.Loading -> false
                    SplashState.Login -> true
                    SplashState.Main -> true
                    SplashState.RegisterDetail -> true
                }
            }
        }


        naverInitializer(
            this@MainActivity,
            BuildConfig.NAVER_OAUTH_CLIENT_ID,
            BuildConfig.NAVER_OAUTH_CLIENT_SECRET,
            BuildConfig.NAVER_OAUTH_CLIENT_NAME
        )


        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.splashUiState.onEach { splashState = it }.collect()
            }


        }


        setContent {
            val systemUiController = rememberSystemUiController()
            viewModel.setDefaultSystemUiController(systemUiController)
            LiftMaterialTheme() {
                LiftApp(
                    splashState = splashState,
                    windowSizeClass = calculateWindowSizeClass(this),
                    appState = rememberAppState(
                        rememberNavController(),
                        connectOAuthFromNaverUseCase,
                        connectOAuthFromKakaoUseCase,
                        dispatcherProvider
                    )
                )
            }
        }


    }
}





