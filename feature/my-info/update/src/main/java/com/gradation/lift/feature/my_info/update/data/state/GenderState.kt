package com.gradation.lift.feature.my_info.update.data.state

import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.UserDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * [GenderState]
 * 사용자의 성별 관련한 상태
 * @property gender 사용자의 성별
 * @since 2023-08-26 13:26:58
 */
class GenderState(userDetail: StateFlow<UserDetail>) {
    var gender: MutableStateFlow<Gender> = MutableStateFlow(userDetail.value.gender)

    internal fun updateMale(): () -> Unit = {
        gender.value = Gender.Male()
    }

    internal fun updateFemale(): () -> Unit = {
        gender.value = Gender.Female()
    }

}