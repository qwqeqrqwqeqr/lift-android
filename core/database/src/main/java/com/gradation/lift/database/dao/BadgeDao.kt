package com.gradation.lift.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gradation.lift.database.entity.badge.BadgeEntity
import com.gradation.lift.database.util.Constants
import kotlinx.coroutines.flow.Flow

interface BadgeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBadge(badgeEntity: BadgeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBadge(vararg workPartEntity: BadgeEntity)

    @Delete
    suspend fun deleteWorkPart(badgeEntity: BadgeEntity)

    @Query("DELETE FROM '${Constants.Entity.BADGE_TABLE_NAME}'")
    suspend fun deleteAllWorkPart()

    @Query("SELECT * FROM `${Constants.Entity.BADGE_TABLE_NAME}`")
    fun getAllBadge(): Flow<List<BadgeEntity>>
}