package com.gradation.lift.domain.usecase.setting

import com.gradation.lift.domain.repository.SettingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAutoLoginSettingUseCase  @Inject constructor(
    private val settingRepository: SettingRepository,
) {
    operator fun invoke():Flow<Boolean>{
        return settingRepository.getAutoLoginSetting()

    }
}