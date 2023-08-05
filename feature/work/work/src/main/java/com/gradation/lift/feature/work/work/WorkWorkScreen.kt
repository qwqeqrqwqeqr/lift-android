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
import com.gradation.lift.feature.work.work.data.WorkSharedViewModel
import com.gradation.lift.feature.work.work.data.WorkWorkViewModel
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkWorkRoute(
    navController: NavController,
    navigateWorkWorkToComplete: () -> Unit,
    navigateWorkToMain: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WorkWorkViewModel = hiltViewModel(),

) {

    val workBackStackEntry =
        remember { navController.getBackStackEntry(Router.WORK_GRAPH_NAME) }
    val sharedViewModel: WorkSharedViewModel = hiltViewModel(workBackStackEntry)

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