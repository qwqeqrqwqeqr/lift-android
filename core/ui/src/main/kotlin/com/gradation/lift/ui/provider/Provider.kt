package com.gradation.lift.ui.provider

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf
import kotlinx.coroutines.CoroutineScope

/**
 * button click action이 존재하지 않는 스낵바에서 만 사용할 것
 * @since 2024-02-15 23:58:06
 */
val LocalInfoSnackbarHostState =
    compositionLocalOf<SnackbarHostState> { error("No SnackbarHostState provided") }
val LocalWarnSnackbarHostState =
    compositionLocalOf<SnackbarHostState> { error("No SnackbarHostState provided") }

/**
 * 전역적인 코루틴 범위
 * @since 2024-02-16 13:35:10
 */
val LocalAppScope =
    compositionLocalOf<CoroutineScope> { error("No CoroutineScope provided") }