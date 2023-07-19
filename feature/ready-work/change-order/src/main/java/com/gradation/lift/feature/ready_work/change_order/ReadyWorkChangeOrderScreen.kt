package com.gradation.lift.feature.ready_work.change_order

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.theme.LiftMaterialTheme

@Composable
fun ReadyWorkChangeOrderRoute(
    navController: NavController,
    navigateToReadyWorkSelection: () -> Unit,
    navigateReadyWorkToWorkGraph: () -> Unit,
    selectedRoutineSetIdList: List<Int>?,
    modifier: Modifier = Modifier,
    viewModel: ReadyWorkChangeOrderViewModel = hiltViewModel(),
) {
    ReadyWorkChangeOrderScreen(
        modifier=modifier
    )
}

@Composable
fun ReadyWorkChangeOrderScreen(
    modifier:Modifier=Modifier,

) {
    Box(

    ) {
        Text(
            text = "ReadyWorkChangeOrder",
            color = Color.Black
        )
    }
}

@Composable
@Preview
fun ReadyWorkChangeOrderScreenPreview(){
    LiftMaterialTheme {
        ReadyWorkChangeOrderScreen(
            modifier = Modifier
        )
    }
}