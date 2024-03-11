package com.gradation.lift.feature.workReady.findWorkCategory.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.button.smallButton.LiftFavoriteFilterSmallButton
import com.gradation.lift.designsystem.component.button.smallButton.LiftPopularFilterSmallButton
import com.gradation.lift.designsystem.component.button.smallButton.LiftRecommendFilterSmallButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.workReady.findWorkCategory.data.state.FilterState
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
internal fun FilterView(
    modifier: Modifier = Modifier,
    favoriteFilter: Boolean,
    recommendFilter: Boolean,
    popularFilter: Boolean,
    filterState: FilterState,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LiftTheme.space.space20),
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        LiftFavoriteFilterSmallButton(modifier = modifier.noRippleClickable {
            filterState.updateFavoriteFilter(!favoriteFilter)
        }, checked = favoriteFilter)
        LiftRecommendFilterSmallButton(modifier = modifier.noRippleClickable {
            filterState.updateRecommendFilter(!recommendFilter)
        }, checked = recommendFilter)
        LiftPopularFilterSmallButton(modifier = modifier.noRippleClickable {
            filterState.updatePopularFilter(!popularFilter)
        }, checked = popularFilter)
    }
}