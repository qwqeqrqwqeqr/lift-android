package com.gradation.lift.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gradation.lift.database.entity.userBadge.UserBadgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserBadgeDao {


    @Query("SELECT * FROM user_badge")
    fun getAllUserBadge(): Flow<List<UserBadgeEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUserBadge(vararg userBadgeEntity: UserBadgeEntity)


    @Query("DELETE FROM user_badge")
    suspend fun deleteAllUserBadge()


    @Query("UPDATE user_badge SET main_flag=:mainFlag  where id=:badgeId;")
    suspend fun updateUserBadgeMainFlag(mainFlag: Boolean, badgeId: Int)

}