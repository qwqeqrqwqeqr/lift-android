package com.gradation.lift.ui.extensions

import android.content.Context
import android.widget.Toast

/**
 * [showToast]
 * 클릭시 토스트 메시지 출력
 * @since 2024-02-16 20:16:43
 */
fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}





