package com.gradation.lift.feature.home.component.weekdate_routine_view.routine_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun EmptyRoutineListView(
    modifier: Modifier = Modifier,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = modifier.padding(36.dp))
        Image(
            painter = painterResource(id = com.gradation.lift.designsystem.R.drawable.open_box),
            contentDescription = "",
            modifier = modifier
                .width(72.dp)
                .height(54.dp)
        )
        Spacer(modifier = modifier.padding(4.dp))
        Text(
            text = "아직 루틴리스트가 없네요...",
            style = LiftTheme.typography.no4,
            color = LiftTheme.colorScheme.no9,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = modifier.padding(8.dp))
        LiftOutlineButton(
            modifier = modifier.fillMaxWidth(),
            onClick = navigateMainGraphToCreateRoutineGraph,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "루틴 만들기",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no4,
            )
            Spacer(modifier = modifier.padding(2.dp))
            Icon(
                painterResource(id = LiftIcon.ChevronRight),
                contentDescription = null,
                modifier = modifier
                    .fillMaxHeight()
                    .width(8.dp)
            )
        }
        Spacer(modifier = modifier.padding(24.dp))

    }
}

