package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.LiftDatabase
import com.gradation.lift.database.dao.UserBadgeDao
import com.gradation.lift.database.data.TestDataGenerator.Badge.USER_BADGE_ENTITY
import com.gradation.lift.database.data.TestDataGenerator.TEST_DATABASE
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
class UserBadgeDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var userBadgeDao: UserBadgeDao


    @Before
    fun setup() {
        hiltRule.inject()
        userBadgeDao = database.userBadgeDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testUserBadgeInsertAndDelete() = runTest {

        with(userBadgeDao.getAllUserBadge().first()) {
            Truth.assertThat(this.size).isEqualTo(0)
        }

        /**
         * insert item
         */
        userBadgeDao.insertAllUserBadge(userBadgeEntity = listOf(USER_BADGE_ENTITY).toTypedArray())

        with(userBadgeDao.getAllUserBadge().first()) {
            Truth.assertThat(this.size).isEqualTo(1)
            Truth.assertThat(this.map { it.mainFlag }.toSet())
                .isEqualTo(setOf(USER_BADGE_ENTITY.mainFlag))
            Truth.assertThat(this.map { it.badgeTimeStamp }.toSet())
                .isEqualTo(setOf(USER_BADGE_ENTITY.badgeTimeStamp))
            Truth.assertThat(this.map { it.color }.toSet())
                .isEqualTo(setOf(USER_BADGE_ENTITY.color))
            Truth.assertThat(this.map { it.id }.toSet()).isEqualTo(setOf(USER_BADGE_ENTITY.id))
            Truth.assertThat(this.map { it.description }.toSet())
                .isEqualTo(setOf(USER_BADGE_ENTITY.description))
            Truth.assertThat(this.map { it.backgroundColor }.toSet())
                .isEqualTo(setOf(USER_BADGE_ENTITY.backgroundColor))
            Truth.assertThat(this.map { it.name }.toSet()).isEqualTo(setOf(USER_BADGE_ENTITY.name))
            Truth.assertThat(this.map { it.hint }.toSet()).isEqualTo(setOf(USER_BADGE_ENTITY.hint))
            Truth.assertThat(this.map { it.url }.toSet()).isEqualTo(setOf(USER_BADGE_ENTITY.url))
        }
        /**
         * delete item
         */
        userBadgeDao.deleteAllUserBadge()
        Truth.assertThat(userBadgeDao.getAllUserBadge().first().size).isEqualTo(0)
    }

    @Test
    fun testUpdateUserBadgeMainFlag() = runTest {


        with(userBadgeDao.getAllUserBadge().first()) {
            Truth.assertThat(this.size).isEqualTo(0)
        }


        /**
         * insert item
         */
        userBadgeDao.insertAllUserBadge(userBadgeEntity = listOf(USER_BADGE_ENTITY).toTypedArray())
        userBadgeDao.updateUserBadgeMainFlag(
            badgeId = USER_BADGE_ENTITY.id,
            mainFlag = !USER_BADGE_ENTITY.mainFlag
        )

        with(userBadgeDao.getAllUserBadge().first()) {
            Truth.assertThat(this.first().mainFlag).isEqualTo(!USER_BADGE_ENTITY.mainFlag)
        }

    }
}