package com.gradation.lift.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gradation.lift.database.entity.badge.BadgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BadgeDao {

    @Query("SELECT * FROM badge")
    fun getAllBadge(): Flow<List<BadgeEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBadge(vararg badgeEntity: BadgeEntity)


    @Query("DELETE FROM badge")
    suspend fun deleteAllBadge()

}