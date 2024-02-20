package com.gradation.lift.designsystem.component.chart

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.gradation.lift.designsystem.component.chart.model.PieRangeValue
import com.gradation.lift.designsystem.component.chart.model.WorkCategoryCount
import com.gradation.lift.designsystem.component.chip.LiftDefaultChip
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftEmptyContainer
import com.gradation.lift.designsystem.component.container.LiftSecondaryContainer
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlin.math.cos
import kotlin.math.sin


/**
 * [LiftPieChart]
 * @param workPartList 운동 부위 리스트
 * @param selectedPart 현재 선택된 운동부위
 * @param selectedWorkCategoryByWorkPart 선택된 운동 부위에 따른 운동 카테고리로 4개만 설정
 * @param sumOfWorkCategoryCountByWorkPart 선택된 운동 부위에 따른 운동 카테고리 운동횟수 총합
 * @param updateSelectedPart 운동 부위 변경
 */
@Composable
fun LiftPieChart(
    modifier: Modifier = Modifier,
    workPartList: List<String>,
    selectedPart: String,
    selectedWorkCategoryByWorkPart: List<WorkCategoryCount>,
    sumOfWorkCategoryCountByWorkPart: Int,
    updateSelectedPart: (String) -> Unit,
) {
    val localDensity = LocalDensity.current

    val backgroundColor = LiftTheme.colorScheme.no5
    val shadowColor = LiftTheme.colorScheme.no57

    val primaryColor = LiftTheme.colorScheme.no4
    val secondaryColor = LiftTheme.colorScheme.no26
    val tertiaryColor = LiftTheme.colorScheme.no29
    val quaternaryColor = LiftTheme.colorScheme.no56

    val stroke = with(localDensity) { LiftTheme.space.space40.toPx() }
    val largeStroke = with(localDensity) { LiftTheme.space.space52.toPx() }
    val largeShadowStroke = with(localDensity) { LiftTheme.space.space56.toPx() }

    val valueList = selectedWorkCategoryByWorkPart.sortedByDescending { it.count }
    val colorList = listOf(primaryColor, secondaryColor, tertiaryColor, quaternaryColor)
    val pieRangeValueList = arrayListOf<PieRangeValue>()

    var selectedWorkCategory: String by remember { mutableStateOf(valueList.first().name) }
    val updateSelectedWorkCategory: (String) -> Unit = { selectedWorkCategory = it }

    run {
        val totalValue = selectedWorkCategoryByWorkPart.sumOf { it.count }
        val maxProgress = 360f
        var temp = 0f
        valueList.map { it ->
            val rate = it.count.toFloat() / totalValue * maxProgress
            pieRangeValueList.add(
                PieRangeValue(
                    value = it,
                    start = temp,
                    rate = rate
                )
            )
            temp += rate
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
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
            ) {
                workPartList.forEach { name ->
                    LiftDefaultChip(
                        text = name,
                        isSelected = selectedPart == name,
                        onClick = { updateSelectedPart(name) }
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
                        pieRangeValueList,
                        colorList,
                        selectedWorkCategory,
                        shadowColor,
                        backgroundColor,
                        largeShadowStroke,
                        largeStroke,
                        stroke
                    )
                }
                pieRangeValueList.forEach {
                    androidx.compose.animation.AnimatedVisibility(visible = it.value.name == selectedWorkCategory) {
                        BubbleView(
                            modifier = modifier,
                            localDensity = localDensity,
                            boxSize = boxSize,
                            pieRangeValue = it
                        )
                    }
                }
                LiftText(
                    modifier = modifier.align(Alignment.Center),
                    text = "${sumOfWorkCategoryCountByWorkPart}회",
                    color = LiftTheme.colorScheme.no9,
                    textAlign = TextAlign.Center,
                    textStyle = LiftTextStyle.No1
                )
            }

            DescriptionView(
                modifier,
                selectedWorkCategory,
                updateSelectedWorkCategory,
                valueList,
                colorList
            )
        }
    }
}

fun DrawScope.pieChart(
    pieRangeValueList: List<PieRangeValue>,
    colorList: List<Color>,
    selectedWorkCategory: String,
    shadowColor: Color,
    backgroundColor: Color,
    largeShadowStroke: Float,
    largeStroke: Float,
    stroke: Float,
) {

    pieRangeValueList.zip(colorList).forEach { it ->
        when (it.first.value.name) {
            selectedWorkCategory -> {
                drawArc(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            shadowColor.copy(0.3f),
                            Color.Transparent,
                        ),
                        tileMode = TileMode.Repeated
                    ),
                    startAngle = it.first.start,
                    sweepAngle = it.first.rate,
                    useCenter = false,
                    style = Stroke(width = largeShadowStroke, cap = StrokeCap.Butt),
                )
                drawArc(
                    color = it.second,
                    startAngle = it.first.start,
                    sweepAngle = it.first.rate,
                    topLeft = Offset(x = 0f, y = 0f),
                    useCenter = false,
                    style = Stroke(width = largeStroke, cap = StrokeCap.Butt)
                )
            }

            else -> {
                drawArc(
                    color = it.second,
                    startAngle = it.first.start,
                    sweepAngle = it.first.rate,
                    topLeft = Offset(x = 0f, y = 0f),
                    useCenter = false,
                    style = Stroke(width = stroke, cap = StrokeCap.Butt)
                )
            }
        }
    }

    drawCircle(
        color = backgroundColor,
        radius = size.minDimension / 2.5f,
    )
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DescriptionView(
    modifier: Modifier = Modifier,
    selectedWorkCategory: String,
    updateSelectedWorkCategory: (String) -> Unit,
    valueList: List<WorkCategoryCount>,
    colorList: List<Color>,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        valueList.zip(colorList).forEach {
            val textColor by animateColorAsState(
                targetValue = if (selectedWorkCategory == it.first.name) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no1,
                label = "textColorAnimation"
            )
            LiftSecondaryContainer(
                modifier = modifier.clickable { updateSelectedWorkCategory(it.first.name) },
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
                        text = it.first.name,
                        color = textColor,
                        textAlign = TextAlign.Center,
                        textStyle = LiftTextStyle.No7
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
    pieRangeValue: PieRangeValue,
) {
    LiftEmptyContainer(
        modifier = modifier.offset(
            y = (
                    with(localDensity) { (boxSize / 4).toPx() } *
                            sin(pieRangeValue.start * Math.PI / 180)
                    ).dp,
            x = (
                    with(localDensity) { (boxSize / 4).toPx() } *
                            cos(pieRangeValue.start * Math.PI / 180)
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
                text = pieRangeValue.value.name,
                color = LiftTheme.colorScheme.no6,
                textAlign = TextAlign.Center,
                textStyle = LiftTextStyle.No5
            )
            LiftText(
                modifier = modifier,
                text = "${pieRangeValue.value.count}회",
                color = LiftTheme.colorScheme.no5,
                textAlign = TextAlign.Center,
                textStyle = LiftTextStyle.No5
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
        workPartList = listOf("가슴", "어깨", "팔", "등", "복근", "하체"),
        selectedPart = "가슴",
        selectedWorkCategoryByWorkPart = listOf(
            WorkCategoryCount("운동1", 10),
            WorkCategoryCount("운동2", 20),
            WorkCategoryCount("운동3", 35),
            WorkCategoryCount("운동4", 20),
        ),
        sumOfWorkCategoryCountByWorkPart = 72,
        updateSelectedPart = {}
    )
}