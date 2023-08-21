package com.gradation.lift.create_routine.profile.routine_profile_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.create_routine.profile.CreateRoutineProfileScreen
import com.gradation.lift.create_routine.profile.data.state.RoutineSetPictureUiState
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.theme.LiftMaterialTheme

@Composable
fun EmptyRoutineProfileList(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = modifier
                .align(alignment = Alignment.CenterHorizontally)
                .clip(shape = RoundedCornerShape(size = 12.dp))
                .background(SkeletonBrush())
                .size(96.dp),
        )
        repeat(5) {
            Spacer(modifier = modifier.padding(16.dp))
            Box(
                modifier = modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .clip(shape = RoundedCornerShape(size = 12.dp))
                    .background(SkeletonBrush())
                    .height(96.dp)
                    .fillMaxWidth(),
            )
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun CreateRoutinePictureScreePreview() {

    LiftMaterialTheme {
        CreateRoutineProfileScreen(
            modifier = Modifier,
            onBackClickTopBar = {},
            onClickRegisterButton = {},
            updateSelectedPicture = {},
            routineSetPictureUiState = RoutineSetPictureUiState.Loading,
            selectedPicture = mutableStateOf(""),
        )
    }
}