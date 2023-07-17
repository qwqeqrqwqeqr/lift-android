package com.gradation.lift.feature.ready_work.selection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftHomeTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme

@Composable
internal fun ReadyWorkSelectionRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ReadyWorkSelectionViewModel = hiltViewModel(),
) {
    ReadyWorkSelectionScreen(
        modifier = modifier,
        onBackClickTopbar = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ReadyWorkSelectionScreen(
    modifier: Modifier = Modifier,
    onBackClickTopbar: () -> Unit,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴리스트 선택",
                onBackClickTopbar = onBackClickTopbar
            )
        },
    ) {
        Column(
            modifier = modifier.padding(it)
        ) {
            Text(
                text = "ReadyWorkChangeOrder",
                color = Color.Black
            )
        }
    }
}

@Composable
@Preview
fun ReadyWorkSelectionPreview() {
    LiftMaterialTheme {
        ReadyWorkSelectionScreen(
            onBackClickTopbar = {}
        )

    }
}