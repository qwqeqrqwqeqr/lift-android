package com.gradation.lift.designsystem.resource

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.gradation.lift.designsystem.R


object LiftIcon{
    val HomeSelected = R.drawable.home_selected
    val HomeUnSelected = R.drawable.home_unselected
    val RoutineSelected = R.drawable.routine_selected
    val RoutineUnSelected = R.drawable.routine_unselected
    val HistorySelected =R.drawable.history_selected
    val HistoryUnSelected = R.drawable.history_unselected
    val MyInfoSelected = R.drawable.my_info_selected
    val MyInfoUnSelected = R.drawable.my_info_unselected



    val LoginKakao = R.drawable.login_kakao
    val LoginNaver = R.drawable.login_naver
    val LoginGoogle = R.drawable.login_google


    val ArrowBack = Icons.Rounded.ArrowBackIos
    val ChevronRight = Icons.Rounded.ChevronRight
}

sealed class Icon {


    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
