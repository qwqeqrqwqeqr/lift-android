package com.gradation.lift


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.common.IntentSenderForResultStarter
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.Task
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.oauth.common.naverInitializer
import com.gradation.lift.state.SplashState
import com.gradation.lift.state.rememberAppState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var dispatcherProvider: DispatcherProvider

    @Inject
    lateinit var appUpdateManager: AppUpdateManager

    @Inject
    lateinit var appUpdateInfoTask: Task<AppUpdateInfo>


    private val activityResultLauncher: ActivityResultLauncher<IntentSenderRequest> =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {}
                else -> {}
            }
        }
    private val intentSenderForResultStarter =
        IntentSenderForResultStarter { intent, _, fillInIntent, flagsMask, flagsValues, _, _ ->
            val request = IntentSenderRequest.Builder(intent)
                .setFillInIntent(fillInIntent)
                .setFlags(flagsValues, flagsMask)
                .build()
            activityResultLauncher.launch(request)
        }


    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    IMMEDIATE,
                    intentSenderForResultStarter,
                    UPDATE_RESULT_CODE,
                )
            }
        }


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
            applicationContext,
            BuildConfig.NAVER_OAUTH_CLIENT_ID,
            BuildConfig.NAVER_OAUTH_CLIENT_SECRET,
            BuildConfig.NAVER_OAUTH_CLIENT_NAME
        )


        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.splashUiState.onEach { splashState = it }.collect()
            }
        }



        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            LiftMaterialTheme {
                LiftApp(
                    splashState = splashState,
                    windowSizeClass = calculateWindowSizeClass(this),
                    appState = rememberAppState(
                        navController = rememberNavController(),
                        systemUiController = rememberSystemUiController(),
                    ),
                    navigateToOssScreen = { with(this) { startActivity(createOssIntent()) } }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    IMMEDIATE,
                    intentSenderForResultStarter,
                    UPDATE_RESULT_CODE
                )
            }
        }
    }

    companion object {
        const val UPDATE_RESULT_CODE = 300
    }
}


private fun Context.createOssIntent(): Intent {
    OssLicensesMenuActivity.setActivityTitle("오픈소스 라이센스")
    return Intent(this, OssLicensesMenuActivity::class.java)
}



