package com.gradation.lift.designsystem.component.chart

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.card.LiftInfoCard
import com.gradation.lift.designsystem.component.chart.model.PieChartValue
import com.gradation.lift.designsystem.component.chart.model.SampleData.PIE_CHART_SAMPLE_DATA
import com.gradation.lift.designsystem.component.chart.model.WorkCategoryCount
import com.gradation.lift.designsystem.component.chart.state.PieChartState
import com.gradation.lift.designsystem.component.chip.LiftDefaultChip
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftEmptyContainer
import com.gradation.lift.designsystem.component.container.LiftPrimaryContainer
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun LiftPieChart(
    modifier: Modifier = Modifier,
    pieChartState: PieChartState,
    sample: Boolean = false,
) {
    val isSample: Boolean = sample || pieChartState.selectedDateTotalWorkCount == 0

    val state = if (isSample) PIE_CHART_SAMPLE_DATA else pieChartState

    val localDensity = LocalDensity.current

    val backgroundColor = LiftTheme.colorScheme.no5
    val shadowColor = LiftTheme.colorScheme.no57

    val primaryColor = LiftTheme.colorScheme.no4
    val secondaryColor = LiftTheme.colorScheme.no26
    val tertiaryColor = LiftTheme.colorScheme.no29
    val quaternaryColor = LiftTheme.colorScheme.no56

    val defaultColor = LiftTheme.colorScheme.no1

    val stroke = with(localDensity) { LiftTheme.space.space40.toPx() }
    val largeStroke = with(localDensity) { LiftTheme.space.space52.toPx() }
    val shadowStroke = with(localDensity) { LiftTheme.space.space56.toPx() }

    val valueList = state.workCategoryCountByWorkPartList.sortedByDescending { it.count }
    val colorList = listOf(primaryColor, secondaryColor, tertiaryColor, quaternaryColor)
    val pieChartValueList = arrayListOf<PieChartValue>()

    var selectedWorkCategory: String by remember { mutableStateOf("") }
    val updateSelectedWorkCategory: (String) -> Unit = { selectedWorkCategory = it }



    run {
        val totalValue = state.workCategoryCountByWorkPartList.sumOf { it.count }
        val maxProgress = 360f
        var temp = 0f
        valueList.zip(colorList).map { it ->

            val tempRate = it.first.count.toFloat() / totalValue * maxProgress

            val startPosition: Float by animateFloatAsState(
                targetValue = temp,
                label = "startPieChartPositionAnimation",
                animationSpec = tween(2000)
            )
            val rate: Float by animateFloatAsState(
                targetValue = tempRate,
                label = "pieChartRateAnimation",
                animationSpec = tween(2000)
            )

            val strokeAnimation: Float by animateFloatAsState(
                targetValue =
                if (it.first.name == selectedWorkCategory) largeStroke else stroke,
                label = "",
                animationSpec = tween(1500)
            )

            val shadowStrokeAnimation: Float by animateFloatAsState(
                targetValue =
                if (it.first.name == selectedWorkCategory) shadowStroke else 0f,
                label = "",
                animationSpec = tween(1500)
            )

            val color: Color by animateColorAsState(
                targetValue = it.second,
                label = "",
                animationSpec = tween(1500)
            )


            val visibleBubbleView: Boolean = (it.first.name == selectedWorkCategory)


            pieChartValueList.add(
                PieChartValue(
                    value = it.first,
                    startPosition = startPosition,
                    rate = rate,
                    stroke = strokeAnimation,
                    shadowStroke = shadowStrokeAnimation,
                    visibleBubbleView = visibleBubbleView,
                    color = color
                )
            )
            temp += tempRate
        }
    }


    LiftDefaultContainer(
        modifier = modifier
            .fillMaxWidth(),
        horizontalPadding = LiftTheme.space.space10,
        verticalPadding = LiftTheme.space.space16
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space32)
        ) {
            if (sample) LiftInfoCard(
                modifier = modifier,
                text = "운동을 진행하면 운동결과에 따른 분석을 볼 수 있어요"
            )

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
            ) {
                state.workPartList.forEach { name ->
                    LiftDefaultChip(
                        text = name,
                        isSelected = state.selectedWorkPart == name,
                        onClick = { state.updateSelectedWorkPart(name) }
                    )
                }
            }
            BoxWithConstraints(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = LiftTheme.space.space32),
                contentAlignment = Alignment.Center,
            ) {
                val boxSize = this.maxWidth / 3

                Canvas(
                    modifier = modifier
                        .height(boxSize)
                        .width(boxSize)
                        .align(Alignment.Center),
                ) {
                    pieChart(
                        pieChartValueList,
                        shadowColor,
                        backgroundColor,
                        stroke,
                        defaultColor
                    )
                }
                pieChartValueList.forEach {
                    androidx.compose.animation.AnimatedVisibility(visible = it.visibleBubbleView) {
                        BubbleView(
                            modifier = modifier,
                            localDensity = localDensity,
                            boxSize = boxSize,
                            pieChartValue = it
                        )
                    }
                }
                LiftText(
                    modifier = modifier.align(Alignment.Center),
                    text = "${if (isSample) "??" else state.sumOfWorkCategoryCountByWorkPart}회",
                    color = LiftTheme.colorScheme.no9,
                    textAlign = TextAlign.Center,
                    textStyle = LiftTextStyle.No1
                )
            }
            if (pieChartState.sumOfWorkCategoryCountByWorkPart == 0) LiftInfoCard(
                modifier = modifier,
                text = "${pieChartState.selectedWorkPart} 운동을 진행하면 분석 결과를 볼 수 있어요"
            )
            else {
                DescriptionView(
                    modifier,
                    selectedWorkCategory,
                    updateSelectedWorkCategory,
                    valueList,
                    colorList,
                    isSample
                )
            }
        }
    }
}

fun DrawScope.pieChart(
    pieChartValueList: List<PieChartValue>,
    shadowColor: Color,
    backgroundColor: Color,
    defaultStroke: Float,
    defaultColor: Color,
) {
    if (pieChartValueList.isEmpty()) {
        drawArc(
            color = defaultColor,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = defaultStroke, cap = StrokeCap.Butt),
        )

        drawCircle(
            color = backgroundColor,
            radius = size.minDimension / 2.5f,
        )
    } else {
        pieChartValueList.forEach { it ->
            drawArc(
                brush = Brush.radialGradient(
                    colors = listOf(
                        shadowColor.copy(0.3f),
                        Color.Transparent,
                    ),
                    tileMode = TileMode.Repeated
                ),
                startAngle = it.startPosition,
                sweepAngle = it.rate,
                useCenter = false,
                style = Stroke(width = it.shadowStroke, cap = StrokeCap.Butt),
            )
            drawArc(
                color = it.color,
                startAngle = it.startPosition,
                sweepAngle = it.rate,
                topLeft = Offset(x = 0f, y = 0f),
                useCenter = false,
                style = Stroke(width = it.stroke, cap = StrokeCap.Butt)
            )

        }

        drawCircle(
            color = backgroundColor,
            radius = size.minDimension / 2.5f,
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DescriptionView(
    modifier: Modifier = Modifier,
    selectedWorkCategory: String,
    updateSelectedWorkCategory: (String) -> Unit,
    valueList: List<WorkCategoryCount>,
    colorList: List<Color>,
    isSample: Boolean,
) {
    val mutableInteractionSource = remember { MutableInteractionSource() }
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        valueList.zip(colorList).forEachIndexed { index, it ->
            val textColor by animateColorAsState(
                targetValue = if (selectedWorkCategory == it.first.name) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no13,
                label = "textColorAnimation"
            )
            LiftPrimaryContainer(
                modifier = modifier.clickable(
                    interactionSource = mutableInteractionSource,
                    indication = null
                ) {
                    if (selectedWorkCategory == it.first.name)
                        updateSelectedWorkCategory("")
                    else
                        updateSelectedWorkCategory(it.first.name)
                },
                horizontalPadding = LiftTheme.space.space6,
                verticalPadding = LiftTheme.space.space6,
                shape = RoundedCornerShape(size = LiftTheme.space.space6),
            ) {
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                ) {
                    Spacer(
                        modifier = modifier
                            .background(it.second, CircleShape)
                            .size(LiftTheme.space.space8)
                    )
                    LiftText(
                        modifier = modifier,
                        text = if (isSample) "진행한 운동 ${index + 1}" else it.first.name,
                        color = textColor,
                        textAlign = TextAlign.Center,
                        textStyle = if (selectedWorkCategory == it.first.name) LiftTextStyle.No8 else LiftTextStyle.No7
                    )
                }
            }
        }
    }
}


@Composable
fun BubbleView(
    modifier: Modifier = Modifier,
    localDensity: Density,
    boxSize: Dp,
    pieChartValue: PieChartValue,
) {
    LiftEmptyContainer(
        modifier = modifier.offset(
            y = (
                    with(localDensity) { (boxSize / 4).toPx() } *
                            sin(pieChartValue.startPosition * Math.PI / 180)
                    ).dp,
            x = (
                    with(localDensity) { (boxSize / 4).toPx() } *
                            cos(pieChartValue.startPosition * Math.PI / 180)
                    ).dp,
        ),
        verticalPadding = LiftTheme.space.space8,
        horizontalPadding = LiftTheme.space.space12,
        backGroundColor = LiftTheme.colorScheme.no9,
        shape = RoundedCornerShape(size = LiftTheme.space.space8),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
        ) {
            LiftText(
                modifier = modifier,
                text = pieChartValue.value.name,
                color = LiftTheme.colorScheme.no6,
                textAlign = TextAlign.Center,
                textStyle = LiftTextStyle.No8
            )
            LiftText(
                modifier = modifier,
                text = "${pieChartValue.value.count}회",
                color = LiftTheme.colorScheme.no5,
                textAlign = TextAlign.Center,
                textStyle = LiftTextStyle.No8
            )
        }
    }
}


@Composable
@Preview(
    name = "SAMSUNG Galaxy S20",
    device = "spec:shape=Normal,width=360,height=800,unit=dp,dpi=563"
)
@Preview(
    name = "SAMSUNG Galaxy S10",
    device = "spec:shape=Normal,width=360,height=740,unit=dp,dpi=360"
)
@Preview(name = "PIXEL_3", device = Devices.PIXEL_3)
fun LiftPieChartPreview(
    modifier: Modifier = Modifier,
) {
    LiftPieChart(
        modifier,
        pieChartState = PIE_CHART_SAMPLE_DATA,
        sample = false
    )
}