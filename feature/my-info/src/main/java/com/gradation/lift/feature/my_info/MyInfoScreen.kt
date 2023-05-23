package com.gradation.lift.feature.my_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
internal fun MyInfoRoute(
    modifier: Modifier = Modifier) {
    MyInfoScreen()
}

@Composable
fun MyInfoScreen(){
    Box(

    ){
        Text(
            text="내정보화면",
            color = Color.Black
        )
    }
}