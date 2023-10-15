package com.gradation.lift.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gradation.lift.database.entity.badge.BadgeEntity
import com.gradation.lift.database.entity.badge.UserBadgeEntity
import com.gradation.lift.database.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface BadgeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBadge(badgeEntity: BadgeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBadge(vararg workPartEntity: BadgeEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserBadge(userBadgeEntity: UserBadgeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUserBadge(vararg userBadgeEntity: UserBadgeEntity)


    @Delete
    suspend fun deleteWorkPart(badgeEntity: BadgeEntity)
    @Delete
    suspend fun deleteUserBadge(userBadgeEntity: UserBadgeEntity)


    @Query("DELETE FROM '${Constants.Entity.BADGE_TABLE_NAME}'")
    suspend fun deleteAllBadge()

    @Query("DELETE FROM '${Constants.Entity.USER_BADGE_TABLE_NAME}'")
    suspend fun deleteAllUserBadge()

    @Query("SELECT * FROM `${Constants.Entity.BADGE_TABLE_NAME}`")
    fun getAllBadge(): Flow<List<BadgeEntity>>

    @Query("SELECT * FROM `${Constants.Entity.USER_BADGE_TABLE_NAME}`")
    fun getAllUserBadge(): Flow<List<UserBadgeEntity>>


}