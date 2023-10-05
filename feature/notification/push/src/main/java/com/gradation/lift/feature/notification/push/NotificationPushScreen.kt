package com.gradation.lift.feature.notification.push

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.notification.component.EmptyPushListView

@Composable
fun NotificationPushRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: NotificationPushViewModel = hiltViewModel(),
) {
    NotificationPushScreen(modifier)
}

@Composable
fun NotificationPushScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = LiftTheme.colorScheme.no5,
    ) {
        EmptyPushListView(modifier)

    }
}

