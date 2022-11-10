package com.gradation.database.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.gradation.database.model.WorkSetEntity

class WorkSetListConverter {
    @TypeConverter
    fun listToJson(value: List<WorkSetEntity>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<WorkSetEntity>::class.java).toList()
}



//class WeekTypeConverter {
//    @TypeConverter
//    fun weekTypeToString(value: WeekEntity?): String? =
//        value?.let(WeekEntity::serializedName)
//
//    @TypeConverter
//    fun stringToNewsResourceType(serializedName: String?): NewsResourceType =
//        serializedName.asNewsResourceType()
//}
