package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.dao.UserDao
import com.gradation.lift.database.data.TestEntityDataGenerator.TEST_DATABASE
import com.gradation.lift.database.data.TestEntityDataGenerator.User.userEntity
import com.gradation.lift.database.di.LiftDatabase
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
import kotlin.Int.Companion.MAX_VALUE

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class UserDaoTest {

    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var userDao: UserDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        userDao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsetUser() = runTest {
        userDao.insertUser(userEntity = userEntity)
        with(userDao.getAllUser().first()) {
            Truth.assertThat(this.size).isEqualTo(1)
        }

    }


    @Test
    fun testUpdateUser() = runTest {
        userDao.insertUser(userEntity = userEntity)
        userDao.updateUser(userEntity.copy(height = Float.MAX_VALUE))
        with(userDao.getAllUser().first()) {
            Truth.assertThat(this.first().height).isEqualTo(Float.MAX_VALUE)
        }
    }

    @Test
    fun testDeleteUser() = runTest {
        userDao.insertUser(userEntity = userEntity)
        userDao.deleteAllUser()
        with(userDao.getAllUser().first()) {
            Truth.assertThat(this.size).isEqualTo(0)
        }
    }


    @Test
    fun testExistUser() = runTest {
        Truth.assertThat(userDao.existUser().first()).isEqualTo(false)
        userDao.insertUser(userEntity = userEntity)
        Truth.assertThat(userDao.existUser().first()).isEqualTo(true)
    }
}

