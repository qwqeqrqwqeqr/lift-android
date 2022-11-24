package com.gradation.lift.database.util

import android.media.Image
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.gradation.lift.model.data.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class RoutineListTypeConverter @Inject constructor(private val moshi: Moshi) {

    @TypeConverter
    fun jsonTypeToRoutineList(value: String): List<Routine>? {
        val listType = Types.newParameterizedType(List::class.java, Routine::class.java)
        val adapter: JsonAdapter<List<Routine>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun routineListToJsonType(type: List<Routine>): String {
        val listType = Types.newParameterizedType(List::class.java, Routine::class.java)
        val adapter: JsonAdapter<List<Routine>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}

class ImageTypeConverter @Inject constructor(private val moshi: Moshi) {

    @TypeConverter
    fun jsonTypeToImageType(value: String): Image? {
        val adapter: JsonAdapter<Image> = moshi.adapter(Image::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun imageTypeToJsonType(type: Image): String {
        val adapter: JsonAdapter<Image> = moshi.adapter(Image::class.java)
        return adapter.toJson(type)
    }
}

class WeekTypeConverter {
    @TypeConverter
    fun weekTypeToJsonType(value: Week): String = value.let(Week::value)

    @TypeConverter
    fun jsonTypeToWeekType(value: String): Week = value.toWeekType()
}

class WorkPartTypeConverter {
    @TypeConverter
    fun workPartToJsonType(value: WorkPart): String = value.let(WorkPart::value)

    @TypeConverter
    fun jsonTypeToWorkPart(value: String): WorkPart = value.toWorkPartType()
}
