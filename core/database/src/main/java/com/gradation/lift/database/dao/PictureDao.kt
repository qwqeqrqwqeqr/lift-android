package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.picture.RoutineSetPictureEntity
import com.gradation.lift.database.model.picture.UserProfilePictureEntity
import com.gradation.lift.database.util.Constants.Entity.ROUTINE_SET_PICTURE_TABLE_NAME
import com.gradation.lift.database.util.Constants.Entity.USER_PROFILE_PICTURE_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface PictureDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUserProfilePicture(vararg userProfilePictureEntity: UserProfilePictureEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRoutineSetPicture(vararg routineSetPictureEntity: RoutineSetPictureEntity)
    


    @Query("DELETE FROM '${USER_PROFILE_PICTURE_TABLE_NAME}'")
    suspend fun deleteAllUserProfilePicture()
    @Query("DELETE FROM '${ROUTINE_SET_PICTURE_TABLE_NAME}'")
    suspend fun deleteAllRoutineSetPicture()

    
    @Query("SELECT * FROM `${USER_PROFILE_PICTURE_TABLE_NAME}`")
     fun getAllUserProfilePicture() : Flow<List<UserProfilePictureEntity>>
    @Query("SELECT * FROM `${ROUTINE_SET_PICTURE_TABLE_NAME}`")
    fun getAllRoutineSetPicture() : Flow<List<RoutineSetPictureEntity>>

}