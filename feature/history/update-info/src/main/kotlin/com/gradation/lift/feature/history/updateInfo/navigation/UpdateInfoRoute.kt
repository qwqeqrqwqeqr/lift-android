package com.gradation.lift.feature.history.updateInfo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.feature.history.updateInfo.data.UpdateInfoViewModel
import com.gradation.lift.feature.history.updateInfo.data.state.UpdateInfoScreenState
import com.gradation.lift.feature.history.updateInfo.data.state.rememberUpdateInfoScreenState

@Composable
internal fun UpdateInfoRoute(
    modifier: Modifier = Modifier,
    viewModel: UpdateInfoViewModel = hiltViewModel(),
    updateInfoScreenState: UpdateInfoScreenState = rememberUpdateInfoScreenState(),
) {


}