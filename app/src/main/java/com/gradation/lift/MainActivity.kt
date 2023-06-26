package com.gradation.lift

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.gradation.lift.designsystem.theme.LiftTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = rememberSystemUiController()
            viewModel.setDefaultSystemUiController(systemUiController)
            LiftTheme()
            {
              LiftApp(
                  systemUiController=systemUiController,
                  mainActivityViewModel = viewModel
              )
            }
        }
    }
}





