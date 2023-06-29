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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.gradation.lift.designsystem.theme.LiftTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()


    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var splashUiState: SplashUiState by mutableStateOf(SplashUiState.Loading)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.splashUiState
                    .onEach {
                        splashUiState = it
                    }
                    .collect()
            }
        }
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                when (splashUiState) {
                    SplashUiState.Loading -> {
                        false
                    }
                    is SplashUiState.Success -> {
                        true
                    }
                }
            }
        }


        setContent {
            val systemUiController = rememberSystemUiController()
            viewModel.setDefaultSystemUiController(systemUiController)
            LiftTheme()
            {
                LiftApp(
                    mainActivityViewModel = viewModel,
                    windowSizeClass = calculateWindowSizeClass(this),
                )
            }
        }
    }
}





