package com.gradation.lift.database.entity.notice

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gradation.lift.database.util.Constants
import com.gradation.lift.database.util.LocalDateTypeConverter
import com.gradation.lift.model.model.notice.Notice
import kotlinx.datetime.LocalDate

@Entity(
    tableName = Constants.Entity.NOTICE_TABLE_NAME
)
data class NoticeEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "category")
    val category: String,

    @TypeConverters(LocalDateTypeConverter::class)
    @ColumnInfo(name = "date")
    val date: LocalDate,

    @ColumnInfo(name = "checked")
    val checked: Boolean = false
) {
    fun toDomain() = Notice(
        id, title, description, category,date
    )
}