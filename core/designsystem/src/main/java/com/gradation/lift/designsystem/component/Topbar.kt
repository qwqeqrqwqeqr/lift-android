package com.gradation.lift.designsystem.component

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.resource.LiftIcon
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
            Text(title)
        },
        modifier = modifier.statusBarsPadding(),
        navigationIcon = {
            LiftIconButton(
                onClick = onBackClick
            ) {
                Icon(
                    painter = painterResource(LiftIcon.ArrowBack),
                    contentDescription = "",
                    tint = Color.Unspecified,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.secondary,
            titleContentColor = MaterialTheme.colorScheme.secondary,
            actionIconContentColor = MaterialTheme.colorScheme.secondary,
        )
    )

}


@Preview
@Composable
fun LiftTopBarPreview() {
    LiftTheme {
        LiftTopBar(
            title = "리프트",
            onBackClick = {}
        )
    }
}

