package com.gradation.lift.create_routine.routine.data.state

import androidx.compose.runtime.MutableState
import com.gradation.lift.ui.mapper.toRepetitionText
import com.gradation.lift.ui.mapper.toWeightText

/**
 * [KeypadState]
 * 키패드에 대한 정보를 나타내는 상태
 * @since 2023-10-12 00:23:41
 */
sealed class KeypadState {
    object None : KeypadState()
    data class Weight(var index: Int, var weight: MutableState<String>) : KeypadState() {
        fun clearNumber() {
            weight.value = ""
        }

        fun appendNumber(value : String) {
            weight.value += value
        }

        fun appendPoint(value : String) {
            weight.value += value
        }

        fun plusNumber(value : String) {
            weight.value = (weight.value.toFloat() + value.toFloat()).toString()
        }

        fun minusNumber(value : String) {
            weight.value = (weight.value.toFloat() - value.toFloat()).toString()
        }

        fun onDone() {
            weight.value = (weight.value.toFloatOrNull()?.toWeightText() ?: "10").toString()
        }
    }

    data class Repetition(var index: Int, var repetition: MutableState<String>) : KeypadState() {
        fun clearNumber() {
            repetition.value = ""
        }

        fun appendNumber(value : String) {
            repetition.value += value
        }

        fun plusNumber(value : String) {
            repetition.value = (repetition.value.toInt() + value.toInt()).toString()
        }

        fun minusNumber(value : String) {
            repetition.value = (repetition.value.toInt() - value.toInt()).toString()
        }

        fun onDone() {
            repetition.value =
                (repetition.value.toIntOrNull()?.toRepetitionText()?.toString() ?: "10").toString()
        }
    }

}
