package com.gradation.lift.feature.home

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        modifier = modifier,
        uiState = uiState
    )
}


@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        when (uiState) {
            is HomeUiState.Success -> Text(text = uiState.userData.toString())
            is HomeUiState.Loading -> Text(text = "로딩중")
            is HomeUiState.Empty -> Text(text = "빔")
            is HomeUiState.Error ->  Text(text = "에러")
        }
    }
}