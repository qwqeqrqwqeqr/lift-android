package com.gradation.lift.feature.badge.badge.data.model

sealed class SortType {
    data class Number(val title: String = "기본") : SortType()
    data class Name(val title: String = "이름순") : SortType()
    data class Oldest(val title: String = "오래된순") : SortType()
    data class Newest(val title: String = "최신순") : SortType()

    fun getTitleName(): String =
        when (this) {
            is Name -> this.title
            is Newest -> this.title
            is Number -> this.title
            is Oldest -> this.title
        }

}
