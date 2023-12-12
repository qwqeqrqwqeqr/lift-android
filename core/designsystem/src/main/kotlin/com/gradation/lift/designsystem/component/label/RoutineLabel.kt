package com.gradation.lift.designsystem.component.label

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun RoutineLabel(modifier: Modifier = Modifier, id: Int) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (id) {
            1 -> Icon(
                painter = painterResource(LiftIcon.RoutineLabel1),
                contentDescription = "",
                tint = Color.Unspecified,
            )

            2 -> Icon(
                painter = painterResource(LiftIcon.RoutineLabel2),
                contentDescription = "",
                tint = Color.Unspecified,
            )

            3 -> Icon(
                painter = painterResource(LiftIcon.RoutineLabel3),
                contentDescription = "",
                tint = Color.Unspecified,
            )

            4 -> Icon(
                painter = painterResource(LiftIcon.RoutineLabel4),
                contentDescription = "",
                tint = Color.Unspecified,
            )

            else -> Icon(
                painter = painterResource(LiftIcon.RoutineLabel5),
                contentDescription = "",
                tint = Color.Unspecified,
            )
        }
    }
}


@Composable
fun CheckedRoutineLabel(modifier: Modifier = Modifier, id: Int) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (id) {
            1 -> Icon(
                painter = painterResource(LiftIcon.CheckedRoutineLabel1),
                contentDescription = "",
                tint = Color.Unspecified,
            )

            2 -> Icon(
                painter = painterResource(LiftIcon.CheckedRoutineLabel2),
                contentDescription = "",
                tint = Color.Unspecified,
            )

            3 -> Icon(
                painter = painterResource(LiftIcon.CheckedRoutineLabel3),
                contentDescription = "",
                tint = Color.Unspecified,
            )

            4 -> Icon(
                painter = painterResource(LiftIcon.CheckedRoutineLabel4),
                contentDescription = "",
                tint = Color.Unspecified,
            )

            else -> Icon(
                painter = painterResource(LiftIcon.CheckedRoutineLabel5),
                contentDescription = "",
                tint = Color.Unspecified,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RoutineLabelPreview() {
    LiftMaterialTheme {
        Column {
            Column(verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)) {
                RoutineLabel(id = 1)
                RoutineLabel(id = 2)
                RoutineLabel(id = 3)
                RoutineLabel(id = 4)
                RoutineLabel(id = 5)
            }
            Column(verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)) {
                CheckedRoutineLabel(id = 1)
                CheckedRoutineLabel(id = 2)
                CheckedRoutineLabel(id = 3)
                CheckedRoutineLabel(id = 4)
                CheckedRoutineLabel(id = 5)
            }
        }
    }
}

