package com.gradation.lift.model.model.work

/**
 * [WorkPart]
 * 운동 부위를 담아둔 모델 (ex: 등, 가슴, 하체, ...)
 * @property id  모델의 고유 아이디 번호
 * @property name 운동 부위 이름
 **/
data class WorkPart(
    val id: Int,
    val name: String,
) {
    companion object {

        val WORK_PART_NAME_LIST = listOf<String>(
            "가슴",
            "등",
            "복근",
            "하체",
            "팔",
            "어깨"
        )
    }
}


