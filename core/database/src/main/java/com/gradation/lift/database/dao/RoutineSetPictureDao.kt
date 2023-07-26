package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.picture.RoutineSetPictureEntity
import com.gradation.lift.database.util.Constants

@Dao
interface RoutineSetPictureDao {


    @Insert
    suspend fun insertUserProfilePicture(routineSetPictureEntity: RoutineSetPictureEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRoutineSetPicture(routineSetPictureEntity: RoutineSetPictureEntity)

    @Delete
    suspend fun deleteRoutineSetPicture(routineSetPictureEntity: RoutineSetPictureEntity)



    @Query("DELETE FROM '${Constants.Entity.ROUTINE_SET_PICTURE_TABLE_NAME}'")
    suspend fun deleteAllRoutineSetPicture()

}