package com.gradation.lift.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.component.label.AllRoutineLabel
import com.gradation.lift.designsystem.component.label.FridayRoutineLabel
import com.gradation.lift.designsystem.component.label.MondayRoutineLabel
import com.gradation.lift.designsystem.component.label.RoutineLabel
import com.gradation.lift.designsystem.component.label.SaturdayRoutineLabel
import com.gradation.lift.designsystem.component.label.SundayRoutineLabel
import com.gradation.lift.designsystem.component.label.ThursdayRoutineLabel
import com.gradation.lift.designsystem.component.label.TuesdayRoutineLabel
import com.gradation.lift.designsystem.component.label.WednesdayRoutineLabel
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.data.state.RoutineUiState
import com.gradation.lift.model.model.date.Weekday

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalLayoutApi::class)
@Composable
fun RoutineView(
    modifier: Modifier,
    routineUiState: RoutineUiState,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailRoutineRouter: (Int) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(LiftIcon.RoutineList),
                    contentDescription = "routine",
                    tint = Color.Unspecified,
                )
                Text(
                    text = "자주 사용하는 루틴",
                    color = LiftTheme.colorScheme.no9,
                    style = LiftTheme.typography.no1
                )
            }

            Row(
                modifier = modifier.noRippleClickable { navigateHomeGraphToRoutineDetailGraph() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {

                Text(
                    text = "전체보기",
                    color = LiftTheme.colorScheme.no2,
                    style = LiftTheme.typography.no6
                )
                Icon(
                    modifier = modifier
                        .width(8.dp)
                        .height(8.dp),
                    painter = painterResource(LiftIcon.ChevronRightSharp),
                    contentDescription = "routineAll",
                    tint = LiftTheme.colorScheme.no2,
                )
            }
        }
        when (routineUiState) {
            RoutineUiState.Empty -> {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(36.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            modifier = modifier.size(72.dp),
                            painter = painterResource(id = R.drawable.open_box),
                            contentDescription = "box",
                        )
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "루틴이 존재하지 않네요...",
                                style = LiftTheme.typography.no2,
                                color = LiftTheme.colorScheme.no2,
                            )
                            Text(
                                text = "루틴을 추가하면 더욱 빠르게 운동을 시작할 수 있어요",
                                style = LiftTheme.typography.no6,
                                color = LiftTheme.colorScheme.no2,
                            )
                        }
                    }
                    LiftOutlineButton(
                        modifier = modifier.fillMaxWidth(),
                        onClick = navigateMainGraphToCreateRoutineGraph,
                        shape = RoundedCornerShape(size = 12.dp),
                        border = 2.dp
                    ) {
                        Text(
                            text = "루틴 추가하기",
                            style = LiftTheme.typography.no3.copy(fontWeight = FontWeight.Bold),
                            color = LiftTheme.colorScheme.no4,
                        )
                    }
                }
            }

            is RoutineUiState.Fail -> {

            }

            RoutineUiState.Loading -> {
                repeat(3) {
                    Spacer(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(96.dp)
                            .background(SkeletonBrush(), RoundedCornerShape(12.dp))
                    )
                }

            }

            is RoutineUiState.Success -> {
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    routineUiState.routineList.forEach {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = 4.dp,
                                    shape = RoundedCornerShape(12.dp),
                                    spotColor = LiftTheme.colorScheme.no34,
                                    ambientColor = LiftTheme.colorScheme.no34
                                )
                                .background(
                                    LiftTheme.colorScheme.no5,
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(16.dp)
                                .noRippleClickable {
                                    navigateHomeGraphToRoutineDetailRoutineRouter(
                                        it.id
                                    )
                                },
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            GlideImage(
                                modifier = modifier.size(60.dp),
                                model = it.picture,
                                contentDescription = "routinePicture"
                            )
                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Row(
                                    modifier = modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = it.name,
                                        color = LiftTheme.colorScheme.no9,
                                        style = LiftTheme.typography.no3
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        it.label.forEach {
                                            RoutineLabel(modifier.size(12.dp), id = it.id)
                                        }
                                    }

                                }
                                if (it.description.isEmpty())
                                    Spacer(modifier = modifier.padding(4.dp))
                                else
                                    Text(
                                        text = it.description,
                                        color = LiftTheme.colorScheme.no9,
                                        style = LiftTheme.typography.no6
                                    )


                                FlowRow(
                                    verticalArrangement = Arrangement.spacedBy(4.dp),
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    if (it.weekday.size == 7)
                                        AllRoutineLabel(modifier)
                                    else {
                                        it.weekday.forEach {
                                            when (it) {
                                                is Weekday.Friday -> FridayRoutineLabel(modifier)
                                                is Weekday.Monday -> MondayRoutineLabel(modifier)
                                                is Weekday.Saturday -> SaturdayRoutineLabel(modifier)
                                                is Weekday.Sunday -> SundayRoutineLabel(modifier)
                                                is Weekday.Thursday -> ThursdayRoutineLabel(modifier)
                                                is Weekday.Tuesday -> TuesdayRoutineLabel(modifier)
                                                is Weekday.Wednesday -> WednesdayRoutineLabel(
                                                    modifier
                                                )

                                                is Weekday.None -> {}
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    LiftOutlineButton(
                        modifier = modifier.fillMaxWidth(),
                        onClick = navigateMainGraphToCreateRoutineGraph,
                        shape = RoundedCornerShape(size = 12.dp),
                        border = 2.dp
                    ) {
                        Text(
                            text = "루틴 추가하기",
                            style = LiftTheme.typography.no3.copy(fontWeight = FontWeight.Bold),
                            color = LiftTheme.colorScheme.no4,
                        )
                    }
                }
            }
        }

    }
}