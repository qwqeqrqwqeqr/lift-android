package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.dao.NoticeDao
import com.gradation.lift.database.data.TestEntityDataGenerator.Notification.noticeEntity
import com.gradation.lift.database.data.TestEntityDataGenerator.Notification.noticeEntityList
import com.gradation.lift.database.data.TestEntityDataGenerator.TEST_DATABASE
import com.gradation.lift.database.di.LiftDatabase
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class NoticeDaoTest {
    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var noticeDao: NoticeDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        noticeDao = database.noticeDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertAllNotice() = runTest {
        noticeDao.insertAllNotice(
            noticeEntity = noticeEntityList.toTypedArray()
        )

        with(noticeDao.getAllNotice().first()) {
            Truth.assertThat(this.size).isEqualTo(1)
            Truth.assertThat(this.map { it.description }.toSet()).isEqualTo(setOf(FAKE_STRING_DATA))
        }
    }

    @Test
    fun testUpdateNotice() = runTest {
        noticeDao.insertAllNotice(
            noticeEntity = noticeEntityList.toTypedArray()
        )
        noticeDao.updateNotice(
            noticeEntity.copy(checked = true)
        )

        with(noticeDao.getAllNotice().first()) {
            Truth.assertThat(this.size).isEqualTo(1)
            Truth.assertThat(this.map { it.checked }.first()).isEqualTo(true)
        }
    }


    @Test
    fun testDeleteAllNotice() = runTest {
        noticeDao.insertAllNotice(
            noticeEntity = noticeEntityList.toTypedArray()
        )
        noticeDao.deleteAllNotice()
        Truth.assertThat(noticeDao.getAllNotice().first().size).isEqualTo(0)
    }


}