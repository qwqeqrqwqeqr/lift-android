package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.dao.PictureDao
import com.gradation.lift.database.data.TestEntityDataGenerator.Picture.routineSetPictureEntity1
import com.gradation.lift.database.data.TestEntityDataGenerator.Picture.routineSetPictureEntity2
import com.gradation.lift.database.data.TestEntityDataGenerator.Picture.routineSetPictureEntityList
import com.gradation.lift.database.data.TestEntityDataGenerator.Picture.userProfilePictureEntity1
import com.gradation.lift.database.data.TestEntityDataGenerator.Picture.userProfilePictureEntity2
import com.gradation.lift.database.data.TestEntityDataGenerator.Picture.userProfilePictureEntityList
import com.gradation.lift.database.data.TestEntityDataGenerator.TEST_DATABASE
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

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class PictureDaoTest {
    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var pictureDao: PictureDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        pictureDao = database.pictureDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


    @Test
    fun testInsertUserProfilePicture() = runTest {
        pictureDao.insertAllUserProfilePicture(userProfilePictureEntity = userProfilePictureEntityList.toTypedArray())

        val result = pictureDao.getAllUserProfilePicture().first()

        Truth.assertThat(result.size).isEqualTo(2)
        Truth.assertThat(result.map { it.id }.toSet()).isEqualTo(
            setOf(
                userProfilePictureEntity1.id,
                userProfilePictureEntity2.id
            )
        )
    }

    @Test
    fun testInsertRoutineSetPicture() = runTest {
        pictureDao.insertAllRoutineSetPicture(routineSetPictureEntity = routineSetPictureEntityList.toTypedArray())

        val result = pictureDao.getAllRoutineSetPicture().first()

        Truth.assertThat(result.size).isEqualTo(2)
        Truth.assertThat(result.map { it.id }.toSet()).isEqualTo(
            setOf(
                routineSetPictureEntity1.id,
                routineSetPictureEntity2.id
            )
        )
    }

    @Test
    fun testDeleteUserProfilePicture() = runTest {
        pictureDao.insertAllUserProfilePicture(userProfilePictureEntity = userProfilePictureEntityList.toTypedArray())
        pictureDao.deleteAllUserProfilePicture()
        val result = pictureDao.getAllUserProfilePicture().first()

        Truth.assertThat(result.size).isEqualTo(0)

    }


    @Test
    fun testDeleteRoutineSetPicture() = runTest {
        pictureDao.insertAllRoutineSetPicture(routineSetPictureEntity = routineSetPictureEntityList.toTypedArray())
        pictureDao.deleteAllRoutineSetPicture()

        val result = pictureDao.getAllRoutineSetPicture().first()

        Truth.assertThat(result.size).isEqualTo(0)

    }
}