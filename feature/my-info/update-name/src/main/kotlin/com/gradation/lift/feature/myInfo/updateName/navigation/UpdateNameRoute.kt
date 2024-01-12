package com.gradation.lift.feature.myInfo.updateName.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.feature.myInfo.updateName.data.UpdateNameViewModel
import com.gradation.lift.feature.myInfo.updateName.ui.UpdateNameScreen

@Composable
fun UpdateNameRoute(
    modifier: Modifier = Modifier,
    viewModel: UpdateNameViewModel = hiltViewModel(),
) {
    BackHandler(onBack = {  })

    UpdateNameScreen(
        modifier,
    )
}

