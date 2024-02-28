package com.gradation.lift.feature.myInfo.cancelMembership.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


/**
 * [CancelMembershipViewModel]
 * @property selectedCancelReasonValue 현재 선택된 취소 이유
 * @property etcText 기타 취소 사유 내용
 * @property etcFlag 기타 사유 여부
 *
 */
@HiltViewModel
class CancelMembershipViewModel @Inject constructor() : ViewModel() {


    private val _selectedCancelReasonValue: MutableStateFlow<String> = MutableStateFlow("")
    val selectedCancelReasonValue: StateFlow<String> = _selectedCancelReasonValue.asStateFlow()

    private val _etcText: MutableStateFlow<String> = MutableStateFlow("")
    val etcText: StateFlow<String> = _etcText.asStateFlow()

    val etcFlag: StateFlow<Boolean> = selectedCancelReasonValue.map { it == ETC_VALUE }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    val selectedFlag: StateFlow<Boolean> =
        selectedCancelReasonValue.map { CANCEL_REASON_LIST.contains(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    private val _bottomSheetView: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val bottomSheetView: StateFlow<Boolean> = _bottomSheetView.asStateFlow()


    val updateSelectedCancelReasonValue: (String) -> Unit =
        { _selectedCancelReasonValue.value = it }
    val updateEtcText: (String) -> Unit = { _etcText.value = it }
    val updateBottomSheetView: (Boolean) -> Unit = { _bottomSheetView.value = it }


    companion object {
        const val ETC_VALUE = "기타"


        val CANCEL_REASON_LIST: List<String> = listOf<String>(
            "혼자 운동할 수 있어요",
            "앱에 오류가 많아요",
            "컨텐츠가 부족해요",
            "앱이 너무 복잡해요",
            "사용하기에 너무 불편햐요",
            ETC_VALUE
        )
    }
}

