package com.gradation.lift.model.picture



/**
 * [RoutineSetPicture]
 * 루틴 세트 프로필에 등록되는 사진들의 정보를 저장하는 모델입니다.
 * @property id  모델의 고유 아이디 번호
 * @property category 해당 사진의 카테고리
 * @property url 해당 사진의 url 주소
 **/
data class RoutineSetPicture(
    val id : Int,
    val category : String,
    val url : String,
)