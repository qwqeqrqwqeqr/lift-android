package com.gradation.lift.feature.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.data.HomeViewModel


@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    Button(onClick = {  }) {
        Text(text = "test")
    }
    HomeScreen(
        modifier = modifier,
    )
}


@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
) {

}

