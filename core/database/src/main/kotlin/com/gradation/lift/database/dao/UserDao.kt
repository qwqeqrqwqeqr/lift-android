package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.entity.user.UserEntity
import com.gradation.lift.database.util.Constants.Entity.USER_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("DELETE FROM '${USER_TABLE_NAME}'")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM `${USER_TABLE_NAME}`")
    fun getAllUser(): Flow<List<UserEntity>>

}