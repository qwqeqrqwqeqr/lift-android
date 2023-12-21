package com.gradation.lift.designsystem.component.keypad

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gradation.lift.designsystem.component.bottomSheet.LiftBottomSheet
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

/**
 * [WeightKeypadContainer]
 * 운동 무게를 조정할 떄 사용하는 키패드 컨테이너
 *
 * [RepetitionKeypadContainer]
 * 운동 횟수를 조정할 떄 사용하는 키패드 컨테이너
 *
 * 두 컨테이너 공통적으로 구현 로직은 하단 파라미터를 토대로 별개로 구현해야함
 *
 * @param clearNumber 텍스트를 전부 삭제
 * @param appendNumber 텍스트에 숫자를 이어 붙힘
 * @param appendPoint  텍스트에 소수점을 이어 붙힘
 * @param plusNumber 현재 텍스트에 키패드 숫자만큼 가감
 * @param minusNumber 현재 텍스트에 키패드 숫자만큼 차감
 * @since 2023-10-11 10:50:06
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightKeypadContainer(
    modifier: Modifier = Modifier,
    clearNumber: () -> Unit,
    appendNumber: (String) -> Unit,
    appendPoint: (String) -> Unit,
    plusNumber: (String) -> Unit,
    minusNumber: (String) -> Unit,
    onDismissRequest: () -> Unit,
) {
    LiftBottomSheet(
        modifier = modifier.fillMaxWidth(),
        tonalElevation= 8.dp,
        onDismissRequest = onDismissRequest,
        scrimColor = LiftTheme.colorScheme.no5.copy(0.0f),
        shape = ShapeDefaults.ExtraSmall,
        dragHandle = null
    ) {
        Row(
            modifier = modifier
                .background(LiftTheme.colorScheme.no5)
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Column(
                modifier
                    .weight(3f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    NumberKeypad(modifier.weight(1f), "1", appendNumber)
                    NumberKeypad(modifier.weight(1f), "2", appendNumber)
                    NumberKeypad(modifier.weight(1f), "3", appendNumber)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    NumberKeypad(modifier.weight(1f), "4", appendNumber)
                    NumberKeypad(modifier.weight(1f), "5", appendNumber)
                    NumberKeypad(modifier.weight(1f), "6", appendNumber)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    NumberKeypad(modifier.weight(1f), "7", appendNumber)
                    NumberKeypad(modifier.weight(1f), "8", appendNumber)
                    NumberKeypad(modifier.weight(1f), "9", appendNumber)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    ClearKeypad(modifier.weight(1f), clearNumber)
                    NumberKeypad(modifier.weight(1f), "0", appendNumber)
                    PointKeypad(modifier.weight(1f), appendPoint)
                }
            }
            Column(
                modifier
                    .weight(2f), verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    MinusKeypad(
                        modifier = modifier.weight(1f),
                        number = "0.5",
                        minusNumber = minusNumber
                    )
                    PlusKeypad(
                        modifier = modifier.weight(1f),
                        number = "0.5",
                        plusNumber = plusNumber
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    MinusKeypad(
                        modifier = modifier.weight(1f),
                        number = "1",
                        minusNumber = minusNumber
                    )
                    PlusKeypad(
                        modifier = modifier.weight(1f),
                        number = "1",
                        plusNumber = plusNumber
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    MinusKeypad(
                        modifier = modifier.weight(1f),
                        number = "5",
                        minusNumber = minusNumber
                    )
                    PlusKeypad(
                        modifier = modifier.weight(1f),
                        number = "5",
                        plusNumber = plusNumber
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    DoneKeypad(modifier = modifier.weight(1f), onDone = onDismissRequest)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepetitionKeypadContainer(
    modifier: Modifier = Modifier,
    clearNumber: () -> Unit,
    appendNumber: (String) -> Unit,
    plusNumber: (String) -> Unit,
    minusNumber: (String) -> Unit,
    onDismissRequest: () -> Unit,
) {
    LiftBottomSheet(
        modifier = modifier,
        tonalElevation= 8.dp,
        scrimColor = LiftTheme.colorScheme.no5.copy(0.0f),
        onDismissRequest = onDismissRequest,
        shape = ShapeDefaults.ExtraSmall,
        dragHandle = null
    ) {
        Row(
            modifier = modifier
                .background(LiftTheme.colorScheme.no5)
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Column(
                modifier
                    .weight(3f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    NumberKeypad(modifier.weight(1f), "1", appendNumber)
                    NumberKeypad(modifier.weight(1f), "2", appendNumber)
                    NumberKeypad(modifier.weight(1f), "3", appendNumber)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    NumberKeypad(modifier.weight(1f), "4", appendNumber)
                    NumberKeypad(modifier.weight(1f), "5", appendNumber)
                    NumberKeypad(modifier.weight(1f), "6", appendNumber)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    NumberKeypad(modifier.weight(1f), "7", appendNumber)
                    NumberKeypad(modifier.weight(1f), "8", appendNumber)
                    NumberKeypad(modifier.weight(1f), "9", appendNumber)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    ClearKeypad(modifier.weight(1f), clearNumber)
                    NumberKeypad(modifier.weight(1f), "0", appendNumber)
                    DefaultKeypad(modifier.weight(1f))
                }
            }
            Column(
                modifier
                    .weight(2f), verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    MinusKeypad(
                        modifier = modifier.weight(1f),
                        number = "1",
                        minusNumber = minusNumber
                    )
                    PlusKeypad(
                        modifier = modifier.weight(1f),
                        number = "1",
                        plusNumber = plusNumber
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    MinusKeypad(
                        modifier = modifier.weight(1f),
                        number = "5",
                        minusNumber = minusNumber
                    )
                    PlusKeypad(
                        modifier = modifier.weight(1f),
                        number = "5",
                        plusNumber = plusNumber
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    MinusKeypad(
                        modifier = modifier.weight(1f),
                        number = "10",
                        minusNumber = minusNumber
                    )
                    PlusKeypad(
                        modifier = modifier.weight(1f),
                        number = "10",
                        plusNumber = plusNumber
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    DoneKeypad(modifier = modifier.weight(1f), onDone = onDismissRequest)
                }
            }
        }
    }
}

@Composable
private fun DefaultKeypad(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier
            .height(48.dp)
            .background(
                LiftTheme.colorScheme.no1,
                shape = RoundedCornerShape(8.dp)
            ),
    )
}


@Composable
private fun ClearKeypad(
    modifier: Modifier = Modifier,
    clearNumber: () -> Unit,
) {
    Box(
        modifier = modifier
            .clickable { clearNumber() }
            .height(48.dp)
            .background(
                LiftTheme.colorScheme.no1,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "C",
            style = LiftTheme.typography.no1.copy(fontSize = 28.sp),
            color = LiftTheme.colorScheme.no12,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun NumberKeypad(
    modifier: Modifier = Modifier,
    number: String,
    appendNumber: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable { appendNumber(number) }
            .height(48.dp)
            .background(
                LiftTheme.colorScheme.no1,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number,
            style = LiftTheme.typography.no1.copy(fontSize = 28.sp),
            color = LiftTheme.colorScheme.no2,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
private fun PointKeypad(
    modifier: Modifier = Modifier,
    appendPoint: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable { appendPoint(".") }
            .height(48.dp)
            .background(
                LiftTheme.colorScheme.no1,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = modifier,
            text = ".",
            style = LiftTheme.typography.no1.copy(fontSize = 28.sp),
            color = LiftTheme.colorScheme.no2,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
private fun PlusKeypad(
    number: String,
    modifier: Modifier = Modifier,
    plusNumber: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable { plusNumber(number) }
            .height(48.dp)
            .background(
                LiftTheme.colorScheme.no13,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = modifier,
            text = "+$number",
            style = LiftTheme.typography.no1.copy(fontSize = 20.sp),
            color = LiftTheme.colorScheme.no5,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun MinusKeypad(
    number: String,
    modifier: Modifier = Modifier,
    minusNumber: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable { minusNumber(number) }
            .height(48.dp)
            .background(
                LiftTheme.colorScheme.no13,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = modifier,
            text = "-$number",
            style = LiftTheme.typography.no1.copy(fontSize = 20.sp),
            color = LiftTheme.colorScheme.no5,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
private fun DoneKeypad(
    modifier: Modifier = Modifier,
    onDone: () -> Unit,
) {
    Box(
        modifier = modifier
            .clickable { onDone() }
            .height(48.dp)
            .background(
                LiftTheme.colorScheme.no4,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(LiftIcon.Check),
            contentDescription = "Done",
            tint = LiftTheme.colorScheme.no5
        )
    }
}

