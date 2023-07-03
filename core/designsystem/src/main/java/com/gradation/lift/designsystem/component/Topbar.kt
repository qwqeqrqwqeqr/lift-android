package com.gradation.lift.designsystem.component

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftTopBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = LiftTheme.colorScheme.no9,
                style = LiftTheme.typography.no1
            )
        },
        modifier = modifier.statusBarsPadding(),
        navigationIcon = {
            LiftIconButton(
                onClick = onBackClick,
                modifier = modifier
            ) {
                Icon(
                    painter = painterResource(LiftIcon.ArrowBack),
                    contentDescription = "",
                    tint = Color.Unspecified,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LiftTheme.colorScheme.no5,
            scrolledContainerColor = LiftTheme.colorScheme.no5,
            navigationIconContentColor = LiftTheme.colorScheme.no9,
            titleContentColor = LiftTheme.colorScheme.no9,
            actionIconContentColor = LiftTheme.colorScheme.no9,
        )
    )

}


@Preview
@Composable
fun LiftTopBarPreview() {
    LiftMaterialTheme {
        LiftTopBar(
            title = "리프트",
            onBackClick = {}
        )
    }
}

