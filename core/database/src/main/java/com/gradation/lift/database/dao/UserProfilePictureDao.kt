package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.UserProfilePictureEntity
import com.gradation.lift.database.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfilePictureDao {


    @Insert
    suspend fun insertUserProfilePicture(userProfilePictureEntity: UserProfilePictureEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUserProfilePicture(userProfilePictureEntity: UserProfilePictureEntity)

    @Delete
    suspend fun deleteUserProfilePicture(userProfilePictureEntity: UserProfilePictureEntity)


    @Query("DELETE FROM '${Constants.Entity.USER_PROFILE_PICTURE_TABLE_NAME}'")
    suspend fun deleteAllUserProfilePicture()

}