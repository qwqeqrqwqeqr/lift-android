package com.gradation.lift.feature.work.complete.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.ui.mapper.toText

@Composable
fun HistoryRoutineView(
    modifier: Modifier = Modifier,
    historyRoutineList: List<CreateHistoryRoutine>,
) {
    Column (modifier.padding(horizontal =  16.dp)){

        Text(
            text = "운동 기록",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no9,
            modifier = modifier.align(Alignment.Start)
        )
        Spacer(modifier = modifier.padding(4.dp))

        historyRoutineList.forEach { historyRoutine ->
            Column(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .border(
                        width = 1.dp,
                        color = LiftTheme.colorScheme.no8,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp),
            ) {
                Text(
                    text = historyRoutine.workCategory,
                    color = LiftTheme.colorScheme.no9,
                    style = LiftTheme.typography.no3
                )
                with(historyRoutine.workSetList) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "세트/평균횟수",
                            style = LiftTheme.typography.no6,
                            color = LiftTheme.colorScheme.no11
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                                ) {
                                    append("$size")
                                }
                                append(" Set  ")
                                withStyle(
                                    style = SpanStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                                ) {
                                    append("${(sumOf { it.repetition } / size)}")
                                }
                                append(" Reps")

                            },
                            style = LiftTheme.typography.no6,
                            color = LiftTheme.colorScheme.no11
                        )

                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "최대 무게",
                            style = LiftTheme.typography.no6,
                            color = LiftTheme.colorScheme.no11
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                                ) {
                                    append((maxBy { it.weight }.weight).toText())
                                }
                                append("kg")
                            },
                            style = LiftTheme.typography.no6,
                            color = LiftTheme.colorScheme.no11
                        )

                    }
                }
            }
            Spacer(modifier = modifier.padding(4.dp))
        }
    }
}