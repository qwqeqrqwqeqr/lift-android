package com.gradation.lift.designsystem.component.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftDefaultBottomBar(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    LiftDefaultContainer(
        modifier = modifier
            .shadow(
                elevation = LiftTheme.space.space6,
                spotColor = LiftTheme.colorScheme.no11,
                ambientColor = LiftTheme.colorScheme.no11
            )
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5),
        shape = RectangleShape,
        verticalPadding = LiftTheme.space.space10,
        horizontalPadding = LiftTheme.space.space20,
        content = { content() }
    )
}

@Composable
@Preview
fun LiftDefaultBottomBarPreview(modifier: Modifier = Modifier) {
    LiftMaterialTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5),
            verticalArrangement = Arrangement.Center
        ) {
            LiftDefaultBottomBar(modifier) {
            }
        }
    }

}