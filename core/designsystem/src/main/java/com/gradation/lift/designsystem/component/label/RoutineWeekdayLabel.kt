package com.gradation.lift.designsystem.component.label

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun MondayRoutineLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.mondayBackgroundColor,
                shape = RoundedCornerShape(size = 30.dp)
            )
            .padding(start = 8.dp, top = 10.dp, end = 8.dp, bottom = 10.dp),
        text = "월요일",
        color = LiftTheme.colorScheme.mondayLabelColor,
        style = LiftTheme.typography.no7.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun TuesdayRoutineLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.tuesdayBackgroundColor,
                shape = RoundedCornerShape(size = 30.dp)
            )
            .padding(start = 8.dp, top = 10.dp, end = 8.dp, bottom = 10.dp),
        text = "화요일",
        color = LiftTheme.colorScheme.tuesdayLabelColor,
        style = LiftTheme.typography.no7.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun WednesdayRoutineLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.wednesdayBackgroundColor,
                shape = RoundedCornerShape(size = 30.dp)
            )
            .padding(start = 8.dp, top = 10.dp, end = 8.dp, bottom = 10.dp),
        text = "수요일",
        color = LiftTheme.colorScheme.wednesdayLabelColor,
        style = LiftTheme.typography.no7.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun ThursdayRoutineLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.thursdayBackgroundColor,
                shape = RoundedCornerShape(size = 30.dp)
            )
            .padding(start = 8.dp, top = 10.dp, end = 8.dp, bottom = 10.dp),
        text = "목요일",
        color = LiftTheme.colorScheme.thursdayLabelColor,
        style = LiftTheme.typography.no7.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun FridayRoutineLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.fridayBackgroundColor,
                shape = RoundedCornerShape(size = 30.dp)
            )
            .padding(start = 8.dp, top = 10.dp, end = 8.dp, bottom = 10.dp),
        text = "금요일",
        color = LiftTheme.colorScheme.fridayLabelColor,
        style = LiftTheme.typography.no7.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun SaturdayRoutineLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.saturdayBackgroundColor,
                shape = RoundedCornerShape(size = 30.dp)
            )
            .padding(start = 8.dp, top = 10.dp, end = 8.dp, bottom = 10.dp),
        text = "토요일",
        color = LiftTheme.colorScheme.saturdayLabelColor,
        style = LiftTheme.typography.no7.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun SundayRoutineLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.sundayBackgroundColor,
                shape = RoundedCornerShape(size = 30.dp)
            )
            .padding(start = 8.dp, top = 10.dp, end = 8.dp, bottom = 10.dp),
        text = "일요일",
        color = LiftTheme.colorScheme.sundayLabelColor,
        style = LiftTheme.typography.no7.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun AllRoutineLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.allBackgroundColor,
                shape = RoundedCornerShape(size = 30.dp)
            )
            .padding(start = 8.dp, top = 10.dp, end = 8.dp, bottom = 10.dp),
        text = "모든요일",
        color = LiftTheme.colorScheme.allLabelColor,
        style = LiftTheme.typography.no7.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun RecentRoutineLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.recentBackgroundColor,
                shape = RoundedCornerShape(size = 30.dp)
            )
            .padding(start = 8.dp, top = 10.dp, end = 8.dp, bottom = 10.dp),
        text = "최근",
        color = LiftTheme.colorScheme.recentLabelColor,
        style = LiftTheme.typography.no7.copy(fontWeight = FontWeight.Bold)
    )
}
@Composable
@Preview(showBackground = true)
fun RoutineWeekdayLabelPreview() {
    LiftMaterialTheme {
        Column {
            MondayRoutineLabel()
            TuesdayRoutineLabel()
            WednesdayRoutineLabel()
            ThursdayRoutineLabel()
            FridayRoutineLabel()
            SaturdayRoutineLabel()
            SundayRoutineLabel()
            AllRoutineLabel()
            RecentRoutineLabel()
        }
    }
}

