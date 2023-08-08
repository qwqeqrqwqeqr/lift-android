package com.gradation.lift.feature.work.complete

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.work.data.model.WorkRestTime
import com.gradation.lift.feature.work.work.data.viewmodel.WorkSharedViewModel
import com.gradation.lift.navigation.Router
import com.gradation.lift.ui.utils.toText
import kotlinx.datetime.LocalTime

@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkCompleteRoute(
    navController: NavController,
    navigateWorkToMain: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WorkCompleteViewModel = hiltViewModel(),
) {

    val workBackStackEntry =
        remember { navController.getBackStackEntry(Router.WORK_GRAPH_NAME) }
    val sharedViewModel: WorkSharedViewModel = hiltViewModel(workBackStackEntry)

    val workTime by sharedViewModel.workRestTime.collectAsStateWithLifecycle()

    WorkCompleteScreen(
        modifier = modifier,
        workTime = workTime,
    )


}

@ExperimentalMaterial3Api
@Composable
fun WorkCompleteScreen(
    modifier: Modifier = Modifier,
    workTime: WorkRestTime,
) {

    Surface(
        color = LiftTheme.colorScheme.no5,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
        ) {
            Box(
                modifier = modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.halo),
                    contentDescription = "",
                    modifier = modifier.fillMaxWidth()
                )
                Image(
                    painter = painterResource(R.drawable.trophy),
                    contentDescription = "",
                    modifier = modifier
                        .size(160.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxSize(),
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("운동을 ")
                        withStyle(
                            style = SpanStyle(
                                color = LiftTheme.colorScheme.no4,
                                fontWeight = FontWeight.Bold
                            ),
                        ) {
                            append("완료")
                        }
                        append("하였습니다!")
                    },
                    style = LiftTheme.typography.no1.copy(
                        fontWeight = FontWeight(400)
                    ),
                    color = LiftTheme.colorScheme.no9,
                    modifier = modifier.align(Alignment.CenterHorizontally)
                )
                Row {
                    Column(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no5)
                            .border(
                                width = 1.dp,
                                color = LiftTheme.colorScheme.no8,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Row() {
                            Icon(
                                painter = painterResource(LiftIcon.Muscle),
                                contentDescription = "",
                            )
                            Text(text = "총 소요시간")
                        }
                        Text(text = workTime.totalTime.toText())
                    }
                }
            }
        }
    }

}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun WorkCompleteScreenPreview() {
    LiftMaterialTheme {
        WorkCompleteScreen(
            modifier = Modifier,
            workTime = WorkRestTime().copy(
                totalTime = LocalTime(2, 30),
                restTime = LocalTime(0, 10, 30),
            ),
        )
    }
}