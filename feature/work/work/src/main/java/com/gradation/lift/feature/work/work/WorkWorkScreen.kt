package com.gradation.lift.feature.work.work

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkWorkRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: WorkWorkViewModel = hiltViewModel(),
) {

    WorkWorkScreen(
        modifier = modifier,
        onBackClickTopBar={}
    )



}

@ExperimentalMaterial3Api
@Composable
fun WorkWorkScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "운동",
                onBackClickTopBar = onBackClickTopBar
            )
        },
    ) {
        Column(
            modifier = modifier
                .background(LiftTheme.colorScheme.no5)
                .padding(it)
                .padding(16.dp)
                .fillMaxSize()
        ) {
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun WorkWorkScreenPreview() {
    LiftMaterialTheme {
        WorkWorkScreen(
            modifier = Modifier,
            onBackClickTopBar = {  },
        )
    }
}