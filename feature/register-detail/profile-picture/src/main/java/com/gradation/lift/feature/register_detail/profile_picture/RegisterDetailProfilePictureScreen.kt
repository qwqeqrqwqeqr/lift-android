package com.gradation.lift.feature.register_detail.profile_picture

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.canvas.NumberCircle
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.register_detail.profile_picture.component.CompleteDialog
import com.gradation.lift.model.picture.UserProfilePicture


@Composable
internal fun RegisterDetailProfilePictureRoute(
    navController: NavController,
    navigateRegisterDetailProfilePictureToUnitOfWeight: () -> Unit,
    navigateRegisterDetailToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailProfilePictureViewModel = hiltViewModel(),
) {

    val onVisibleDialog = viewModel.onVisibleDialog.collectAsStateWithLifecycle()
    val profilePictureList = viewModel.profilePictureList.collectAsStateWithLifecycle()
    val selectedProfile = viewModel.selectedProfile.collectAsStateWithLifecycle()
    val navigationCondition = viewModel.navigationCondition.collectAsStateWithLifecycle()



    RegisterDetailProfilePictureScreen(
        modifier = modifier,
        onVisibleDialog = onVisibleDialog,
        profilePictureList = profilePictureList,
        selectedProfile = selectedProfile,
        navigationCondition = navigationCondition,
        onUpdateSelectedProfile = viewModel.updateSelectedProfile(),
        onClickCompleteDialogButton = navigateRegisterDetailToHome,
        onBackClickTopBar = navigateRegisterDetailProfilePictureToUnitOfWeight,
        onCompleteButtonClick = { viewModel.createUserDetail(navController) }
    )


    BackHandler(onBack = {
        navigateRegisterDetailProfilePictureToUnitOfWeight()
    })

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
internal fun RegisterDetailProfilePictureScreen(
    modifier: Modifier = Modifier,
    onVisibleDialog: State<Boolean>,
    profilePictureList: State<List<UserProfilePicture>>,
    selectedProfile: State<String>,
    navigationCondition : State<Boolean>,
    onUpdateSelectedProfile: (String) -> Unit,
    onClickCompleteDialogButton: () -> Unit,
    onBackClickTopBar: () -> Unit,
    onCompleteButtonClick: () -> Unit,
) {
    if (onVisibleDialog.value) {
        Surface(
            color = LiftTheme.colorScheme.no8,
            modifier = modifier.fillMaxSize()
        ) {
            CompleteDialog(onClickCompleteDialogButton = onClickCompleteDialogButton)
        }
    } else {
        Scaffold(
            topBar = {
                LiftBackTopBar(
                    title = "추가정보 입력",
                    onBackClickTopBar = onBackClickTopBar
                )
            },
        ) { padding ->
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .padding(padding)
                    .fillMaxSize()
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)) {
                    repeat(5) {
                        NumberCircle(number = it + 1, checked = it + 1 == 5)
                    }

                }
                Spacer(modifier = modifier.padding(28.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(color = LiftTheme.colorScheme.no4),
                        ) {
                            append("프로필 사진")
                        }
                        append("을 등록해주세요")
                    },
                    style = LiftTheme.typography.no1,
                    color = LiftTheme.colorScheme.no11,
                )
                Spacer(modifier = modifier.padding(30.dp))


                if (selectedProfile.value.isEmpty()) {
                    Box(
                        modifier
                            .clip(CircleShape)
                            .size(96.dp)
                            .background(
                                LiftTheme.colorScheme.no1
                            )
                            .align(Alignment.CenterHorizontally)
                    )
                } else {
                    GlideImage(
                        model = selectedProfile.value,
                        contentDescription = "",
                        modifier = modifier
                            .clip(CircleShape)
                            .size(96.dp)
                            .align(Alignment.CenterHorizontally)

                    )
                }

                Spacer(modifier = modifier.padding(13.dp))



                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    modifier = modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(profilePictureList.value) { item ->
                        if (item.url == selectedProfile.value) {
                            GlideImage(
                                model = item.url,
                                contentDescription = "",
                                modifier = modifier
                                    .border(width = 3.dp, color = LiftTheme.colorScheme.no4, shape = CircleShape)
                                    .clip(CircleShape)

                            )
                        } else {
                            GlideImage(
                                model = item.url,
                                contentDescription = "",
                                modifier = modifier
                                    .clip(CircleShape)
                                    .clickable(
                                        onClick = { onUpdateSelectedProfile(item.url) }
                                    )
                            )
                        }

                    }
                }



                Spacer(modifier = modifier.padding(18.dp))


                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = onCompleteButtonClick,
                    enabled = navigationCondition.value
                ) {
                    Text(
                        text = "완료",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun PreviewRegisterDetailProfilePictureScreen() {
    LiftMaterialTheme {
        RegisterDetailProfilePictureScreen(
            onVisibleDialog = mutableStateOf(false),
            profilePictureList = mutableStateOf(
                listOf(
                    UserProfilePicture("1"),
                    UserProfilePicture("1"),
                    UserProfilePicture("1"),
                    UserProfilePicture("1"),
                    UserProfilePicture("1"),
                )
            ),
            selectedProfile = mutableStateOf(""),
            navigationCondition= mutableStateOf(false),
            onUpdateSelectedProfile = {},
            onClickCompleteDialogButton = {},
            onBackClickTopBar = {},
            onCompleteButtonClick = {}
        )
    }
}
