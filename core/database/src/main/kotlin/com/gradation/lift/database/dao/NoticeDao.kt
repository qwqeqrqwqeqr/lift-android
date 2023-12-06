package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.entity.notice.NoticeEntity
import com.gradation.lift.database.util.Constants.Entity.NOTICE_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface NoticeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNotice(vararg noticeEntity: NoticeEntity)

    @Query("DELETE FROM '${NOTICE_TABLE_NAME}'")
    suspend fun deleteAllNotice()

    @Query("SELECT * FROM `${NOTICE_TABLE_NAME}`")
    fun getAllNotice(): Flow<List<NoticeEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNotice(noticeEntity: NoticeEntity)


}