package com.gradation.lift.feature.home.badge.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.badge.CreateUserBadgeUseCase
import com.gradation.lift.domain.usecase.date.GetNowUseCase
import com.gradation.lift.feature.home.badge.data.state.CreateUserBadgeState
import com.gradation.lift.model.model.badge.CreateUserBadge
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BadgeViewModel @Inject constructor(
    private val createUserBadgeUseCase: CreateUserBadgeUseCase,
    private val getNowUseCase: GetNowUseCase
) : ViewModel() {


    val createUserBadgeState: MutableStateFlow<CreateUserBadgeState> =
        MutableStateFlow(CreateUserBadgeState.None)

    fun createUserBadge(): (Int) -> Unit = {
        viewModelScope.launch {
            CreateUserBadge(id = it, badgeTimeStamp = getNowUseCase()).apply {
                createUserBadgeUseCase(this).collect {
                    when (it) {
                        is DataState.Fail -> createUserBadgeState.value =
                            CreateUserBadgeState.Fail(it.message)

                        is DataState.Success -> createUserBadgeState.value =
                            CreateUserBadgeState.Success
                    }
                }
            }
        }
    }


}