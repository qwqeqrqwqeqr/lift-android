package com.gradation.lift.feature.my_info

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

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