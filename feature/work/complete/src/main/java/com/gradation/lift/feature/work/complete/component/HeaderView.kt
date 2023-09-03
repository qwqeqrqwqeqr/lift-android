package com.gradation.lift.feature.work.complete.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.complete.WorkCompleteScreen
import com.gradation.lift.feature.work.work.data.model.WorkRestTime

@Composable
fun HeaderView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Image(
            painter = painterResource(R.drawable.halo_trophy),
            contentDescription = "",
            modifier = modifier
                .fillMaxWidth()
        )
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
            modifier = modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun WorkCompleteScreenPreview() {
    LiftMaterialTheme {
        WorkCompleteScreen(
            score = 5,
            comment = "",
            commentValidator = Validator(),
            historyWorkRestTime = WorkRestTime(),
            historyRoutineList = emptyList(),
            updateScore = {},
            updateComment = {},
            createWorkHistory = {},
            scrollState = rememberScrollState(),
            snackbarHostState = SnackbarHostState(),
            focusManager = LocalFocusManager.current
        )
    }
}