package com.gradation.lift.domain.usecase.setting

import com.gradation.lift.domain.repository.SettingRepository
import javax.inject.Inject

class SetAutoLoginSettingUseCase @Inject constructor(
    private val settingRepository: SettingRepository,
) {
    suspend operator fun invoke(value: Boolean) {
        return settingRepository.setAutoLoginSetting(value)

    }
}