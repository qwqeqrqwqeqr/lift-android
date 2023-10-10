package com.gradation.lift.feature.work.change_order

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.change_order.data.WorkChangeOrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkChangeOrderRoute(
    navController: NavController,

    modifier: Modifier = Modifier,
    viewModel: WorkChangeOrderViewModel = hiltViewModel(),
) {

    WorkChangeOrderScreen(
        modifier = modifier,
        onBackClickTopBar = {},
        onClickStartWork = {
        },
    )

    BackHandler(
        onBack = {}
    )


}

@ExperimentalMaterial3Api
@Composable
fun WorkChangeOrderScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit,
    onClickStartWork: () -> Unit,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴 선택",
                onBackClickTopBar = onBackClickTopBar
            )
        },
        floatingActionButton = {
            LiftButton(
                contentPadding = PaddingValues(
                    start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp
                ),
                onClick = onClickStartWork,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                    ),
            ) {
                Text(
                    text = "운동시작하기",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no5,
                )
                Spacer(modifier = modifier.padding(2.dp))
                Icon(
                    painterResource(id = LiftIcon.ChevronRight),
                    contentDescription = null,
                    modifier = modifier
                        .align(CenterVertically)
                        .fillMaxHeight()
                        .width(8.dp)
                )

            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = modifier
                .background(LiftTheme.colorScheme.no5)
                .padding(it)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "운동순서",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = modifier.padding(8.dp))
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun WorkChangeOrderScreenPreview() {
    LiftMaterialTheme {
        WorkChangeOrderScreen(
            modifier = Modifier,
            onBackClickTopBar = { },
            onClickStartWork = {},
        )
    }
}