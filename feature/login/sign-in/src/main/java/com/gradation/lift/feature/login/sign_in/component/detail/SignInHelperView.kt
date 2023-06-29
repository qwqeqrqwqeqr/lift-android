package com.gradation.lift.feature.login.sign_in.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignInHelperView(
    modifier: Modifier = Modifier,
    onClickFindEmail: () -> Unit,
    onClickFindPassword: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "자동 로그인",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        FindIdPasswordView(
            onClickFindEmail = onClickFindEmail,
            onClickFindPassword = onClickFindPassword
        )
    }
}