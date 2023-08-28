package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.model.model.picture.UserProfilePicture
import kotlinx.coroutines.flow.Flow

/**
 * [PictureRepository]
 * 사진 정보를 불러오는 저장소
 */
interface PictureRepository {

    /**
     * [getUserProfilePicture]
     * 사용자가 사용할 수 있는 프로필 사진 리스트들을 불러옴
     * @since  2023-08-28 18:28:32
     */
    fun getUserProfilePicture(): Flow<DataState<List<UserProfilePicture>>>

    /**
     * [getRoutineSetPicture]
     * 사용자가 사용할 수 있는 프로필 사진 리스트들을 불러옴
     * @since  2023-08-28 18:28:32
     */
    fun getRoutineSetPicture(): Flow<DataState<List<RoutineSetPicture>>>
}