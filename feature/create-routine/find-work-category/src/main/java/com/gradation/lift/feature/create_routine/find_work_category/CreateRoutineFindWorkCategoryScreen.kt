package com.gradation.lift.feature.create_routine.find_work_category

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftFilterChip
import com.gradation.lift.designsystem.component.LiftSearchTextField
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.create_routine.data.CreateRoutineSharedViewModel
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CreateRoutineFindWorkCategoryRoute(
    navController: NavController,
    navigateCreateRoutineFindWorkCategoryToRoot: () -> Unit,
    navigateCreateRoutineFindWorkCategoryToRoutine: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineFindWorkCategoryViewModel = hiltViewModel()
) {
    val crateRoutineBackStackEntry =
        remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)
    CreateRoutineFindWorkCategoryScreen(
        modifier = modifier,
        onBackClickTopBar = navigateCreateRoutineFindWorkCategoryToRoot
    )

    BackHandler(onBack = navigateCreateRoutineFindWorkCategoryToRoot)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRoutineFindWorkCategoryScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "운동부위 선택",
                onBackClickTopBar = onBackClickTopBar,
            )
        }, modifier = modifier.fillMaxSize()
    ) { padding ->
        Surface(
            color = LiftTheme.colorScheme.no5,
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                LiftSearchTextField(
                    modifier = modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(
                            text = "찾으시는 운동을 검색해주세요",
                            color = LiftTheme.colorScheme.no2,
                            style = LiftTheme.typography.no6,
                        )
                    },
                    onClickSearch = {}
                )
                Row {

                }

            }
        }

    }

}


@Composable
@Preview
fun CreateRoutineFindWorkCategoryScreenPreview() {
    LiftMaterialTheme {
        CreateRoutineFindWorkCategoryScreen(
            modifier = Modifier,
            onBackClickTopBar = {}
        )
    }
}