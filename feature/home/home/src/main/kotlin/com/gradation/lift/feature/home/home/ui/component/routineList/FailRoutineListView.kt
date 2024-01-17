package com.gradation.lift.feature.home.home.ui.component.routineList

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.gradation.lift.feature.home.home.ui.component.RoutineListHeaderView



fun LazyListScope.failRoutineListView(
    modifier:Modifier=Modifier,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
){
    item {
        RoutineListHeaderView(modifier, navigateHomeGraphToRoutineDetailGraph)
    }
}