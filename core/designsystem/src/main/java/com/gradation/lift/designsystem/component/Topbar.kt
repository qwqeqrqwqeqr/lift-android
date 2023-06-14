package com.gradation.lift.designsystem.component

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.resource.Icon
import com.gradation.lift.designsystem.resource.LiftIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftTopBar(
    title : String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(title)
        },
        modifier = modifier.statusBarsPadding(),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon.ImageVectorIcon(LiftIcon.ArrowBack)
            }
        },
    )
}

//TODO top app bar 전용 color 추가
