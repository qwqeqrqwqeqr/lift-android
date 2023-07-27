package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.user.UserEntity
import com.gradation.lift.database.util.Constants

interface UserDao {

    @Insert
    suspend fun insertUser(userEntity: UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(userEntity: UserEntity)

    @Delete
    suspend fun deleteUser(userEntity: UserEntity)


    @Query("DELETE FROM '${Constants.Entity.USER_TABLE_NAME}'")
    suspend fun deleteAllUser()
}