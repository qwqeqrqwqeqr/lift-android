package com.gradation.lift.feature.work.work.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftCloseTopBar
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.model.utils.ModelDataGenerator

@ExperimentalMaterial3Api
@Composable
fun WorkListScreen(
    modifier: Modifier = Modifier,
    previousScreen: Boolean,
    onCloseClickTopBar: () -> Unit,
) {
    Scaffold(
        topBar = {
            LiftCloseTopBar(
                title = null,
                onCloseClickTopBar = onCloseClickTopBar
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(LiftIcon.List),
                        contentDescription = "",
                        tint = Color.Unspecified,
                    )
                }
            }
        },
    ) {
        Surface(
            color = LiftTheme.colorScheme.no5,
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .padding(it)
            ) {

            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun WorkListScreenPreview() {
    LiftMaterialTheme {
        WorkWorkScreen(
            modifier = Modifier,
            onCloseClickTopBar = { },
            routineSetRoutine = ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList
        )
    }
}