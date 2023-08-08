package com.gradation.lift.feature.work.work.component.work_work

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.ToggleCheckbox
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.work.data.model.WorkSetSelection

@Composable
fun WorkSetListView(
    modifier: Modifier = Modifier,
    updateCheckedWorkSet: ((Pair<Int, Int>), Boolean) -> Unit,
    workSetList: List<WorkSetSelection>,
) {
    Column(
        modifier = modifier.fillMaxWidth(),

        ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Set",
                style = LiftTheme.typography.no2,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(1f)
            )
            Text(
                text = "Kg",
                style = LiftTheme.typography.no2,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(1f)
            )
            Text(
                text = "Reps",
                style = LiftTheme.typography.no2,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(1f)
            )
            Text(
                text = "완료",
                style = LiftTheme.typography.no2,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(1f)
            )
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(workSetList) { workSet ->
                Row(
                    modifier = modifier
                        .background(LiftTheme.colorScheme.no5)
                        .border(
                            width = 1.dp,
                            color = LiftTheme.colorScheme.no8,
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        text = "${workSet.set.second}",
                        style = LiftTheme.typography.no2,
                        color = if (workSet.selected) LiftTheme.colorScheme.no2 else LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "${workSet.weight}",
                        style = LiftTheme.typography.no2,
                        color = if (workSet.selected) LiftTheme.colorScheme.no2 else LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )

                    Text(
                        text = "${workSet.repetition}",
                        style = LiftTheme.typography.no2,
                        color = if (workSet.selected) LiftTheme.colorScheme.no2 else LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                    ToggleCheckbox(
                        checked = workSet.selected,
                        onCheckedChange = { updateCheckedWorkSet(workSet.set, it) },
                        modifier = modifier
                            .size(22.dp)
                            .weight(1f)

                    )
                }
            }
        }
    }

}
