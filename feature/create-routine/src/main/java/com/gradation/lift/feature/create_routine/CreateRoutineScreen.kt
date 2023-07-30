package com.gradation.lift.feature.create_routine

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.navigation.navigation.navigateCreateRoutineToMain


@Composable
fun CreateRoutineRoute(
    navController: NavController,
    sharedViewModel: CreateRoutineSharedViewModel,
    navigateCreateRoutineRootToFindWorkCategory: () -> Unit,
    navigateCreateRoutineRootToProfile: () -> Unit,
    navigateCreateRoutineToMain: () -> Unit,
    modifier: Modifier = Modifier,
) {


   val name =  sharedViewModel.name.collectAsStateWithLifecycle()
    val update = sharedViewModel.updateName()

    CreateRoutineRoutineSetScreen(
        onBackClickTopBar = { navController.navigateCreateRoutineToMain() },
        click = navigateCreateRoutineRootToProfile,
        update = update,
        modifier = modifier,
        name =name
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateRoutineRoutineSetScreen(
    onBackClickTopBar: () -> Unit,
    click : () -> Unit,
    update : (String) -> Unit,
    modifier: Modifier = Modifier,
    name : State<String>

    ) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        Scaffold(
            topBar = {
                LiftBackTopBar(
                    title = "루틴리스트 만들기",
                    onBackClickTopBar = onBackClickTopBar,
                )
            },
        ) { it ->

            Column(modifier = modifier.padding(it)) {

                Text(text = name.value)
                LiftTextField(value = name.value, onValueChange = update, placeholder = { Text(text = "z") })
                Button(onClick = click) {

                }
            }
        }
    }
}

