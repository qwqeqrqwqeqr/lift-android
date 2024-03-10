package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.LiftDatabase
import com.gradation.lift.database.dao.BadgeDao
import com.gradation.lift.database.data.TestDataGenerator.Badge.BADGE_ENTITY
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
class BadgeDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var badgeDao: BadgeDao


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
    fun testBadgeInsertAndDelete() = runTest {

        Truth.assertThat(badgeDao.getAllBadge().first().size).isEqualTo(0)

        /**
         * insert item
         */
        badgeDao.insertAllBadge(badgeEntity = listOf(BADGE_ENTITY).toTypedArray())

        with(badgeDao.getAllBadge().first()) {
            Truth.assertThat(this.size).isEqualTo(1)
            Truth.assertThat(this.map { it.color }.toSet()).isEqualTo(setOf(BADGE_ENTITY.color))
            Truth.assertThat(this.map { it.id }.toSet()).isEqualTo(setOf(BADGE_ENTITY.id))
            Truth.assertThat(this.map { it.description }.toSet())
                .isEqualTo(setOf(BADGE_ENTITY.description))
            Truth.assertThat(this.map { it.backgroundColor }.toSet())
                .isEqualTo(setOf(BADGE_ENTITY.backgroundColor))
            Truth.assertThat(this.map { it.name }.toSet()).isEqualTo(setOf(BADGE_ENTITY.name))
            Truth.assertThat(this.map { it.hint }.toSet()).isEqualTo(setOf(BADGE_ENTITY.hint))
            Truth.assertThat(this.map { it.url }.toSet()).isEqualTo(setOf(BADGE_ENTITY.url))
        }
        /**
         * delete item
         */
        badgeDao.deleteAllBadge()
        Truth.assertThat(badgeDao.getAllBadge().first().size).isEqualTo(0)
    }

}