package com.gradation.lift.feature.work.work.component.work_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.gradation.lift.feature.work.work.data.WorkScreenState

@ExperimentalMaterial3Api
@Composable
fun WorkListScreen(
    modifier: Modifier = Modifier,
    onCloseClickTopBar: () -> Unit,
) {
    Scaffold(
        topBar = {
            LiftCloseTopBar(
                title = "운동 조회",
                onCloseClickTopBar =  onCloseClickTopBar
            ) {
                Icon(
                    painter = painterResource(LiftIcon.Timer),
                    contentDescription = "",
                    tint = Color.Unspecified,
                )
                Spacer(modifier = modifier.padding(5.dp))
                Text(
                    text = "00:00",
                    style = LiftTheme.typography.no2,
                    color = LiftTheme.colorScheme.no9
                )
                Spacer(modifier = modifier.padding(8.dp))

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
        WorkListScreen(
            modifier = Modifier,
            onCloseClickTopBar = { },
        )
    }
}