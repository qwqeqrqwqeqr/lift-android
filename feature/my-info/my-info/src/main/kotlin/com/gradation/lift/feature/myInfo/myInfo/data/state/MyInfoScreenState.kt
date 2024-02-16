package com.gradation.lift.feature.myInfo.myInfo.data.state

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.gradation.lift.ui.provider.LocalWarnSnackbarHostState

@Composable
fun rememberMyInfoScreenState(
    context: Context = LocalContext.current,
    snackbarHostState: SnackbarHostState = LocalWarnSnackbarHostState.current,
    appScope: SnackbarHostState = LocalWarnSnackbarHostState.current,
): MyInfoScreenState =
    remember(context, snackbarHostState) {
        MyInfoScreenState(
            context,
            snackbarHostState
        )
    }


data class MyInfoScreenState(
    val context: Context,
    val snackbarHostState: SnackbarHostState,
) {
    val versionName: String =
        context
            .packageManager
            .getPackageInfo(context.packageName, 0)
            .versionName ?: ""
}

