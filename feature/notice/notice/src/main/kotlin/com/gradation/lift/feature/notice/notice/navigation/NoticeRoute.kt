package com.gradation.lift.feature.notice.notice.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.notice.notice.data.NoticeUiState
import com.gradation.lift.feature.notice.notice.data.NoticeViewModel
import com.gradation.lift.model.model.notification.Notice

@Composable
fun NoticeRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: NoticeViewModel = hiltViewModel(),
) {


    val noticeUiState: NoticeUiState by viewModel.noticeUiState.collectAsStateWithLifecycle()
    val updateSelectedNotice: (Notice) -> Unit = viewModel.updateSelectedNotice()

}