package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.dao.BadgeDao
import com.gradation.lift.database.data.TestEntityDataGenerator.Badge.badgeEntityList
import com.gradation.lift.database.data.TestEntityDataGenerator.Badge.userBadgeEntityList
import com.gradation.lift.database.data.TestEntityDataGenerator.TEST_DATABASE
import com.gradation.lift.database.di.LiftDatabase
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_COLOR_DATA
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
class BadgeDaoTest {
    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var badgeDao: BadgeDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        badgeDao = database.badgeDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertAllBadge() = runTest {
        badgeDao.insertAllBadge(
            badgeEntity = badgeEntityList.toTypedArray()
        )

        with(badgeDao.getAllBadge().first()) {
            Truth.assertThat(this.size).isEqualTo(1)
            Truth.assertThat(this.map { it.color }.toSet()).isEqualTo(setOf(FAKE_COLOR_DATA))
        }
    }

    @Test
    fun testInsertAllUserBadge() = runTest {
        badgeDao.insertAllUserBadge(
            userBadgeEntity = userBadgeEntityList.toTypedArray()
        )

        with(badgeDao.getAllUserBadge().first()) {
            Truth.assertThat(this.size).isEqualTo(1)
            Truth.assertThat(this.map { it.badge.color }.toSet()).isEqualTo(setOf(FAKE_COLOR_DATA))
        }
    }


    @Test
    fun testDeleteAllBadge() = runTest {
        badgeDao.insertAllBadge(
            badgeEntity = badgeEntityList.toTypedArray()
        )
        badgeDao.deleteAllBadge()
        Truth.assertThat(badgeDao.getAllBadge().first().size).isEqualTo(0)
    }


    @Test
    fun testDeleteAllUserBadge() = runTest {
        badgeDao.insertAllUserBadge(
            userBadgeEntity = userBadgeEntityList.toTypedArray()
        )
        badgeDao.deleteAllUserBadge()
        Truth.assertThat(badgeDao.getAllUserBadge().first().size).isEqualTo(0)
    }

}