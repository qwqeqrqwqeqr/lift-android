package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.dao.RoutineSetRoutineDao
import com.gradation.lift.database.data.TestEntityDataGenerator.Routine.routineEntity1
import com.gradation.lift.database.data.TestEntityDataGenerator.Routine.routineEntityList
import com.gradation.lift.database.data.TestEntityDataGenerator.RoutineSetRoutine.routineSetRoutineEntity1
import com.gradation.lift.database.data.TestEntityDataGenerator.RoutineSetRoutine.routineSetRoutineEntity2
import com.gradation.lift.database.data.TestEntityDataGenerator.RoutineSetRoutine.routineSetRoutineEntityList
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
class RoutineSetRoutineDaoTest {
    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var routineSetRoutineDao: RoutineSetRoutineDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        routineSetRoutineDao = database.routineSetRoutineDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertRoutineSetRoutine() = runTest {
        routineSetRoutineDao.insert(
            routineSetRoutineEntity = routineSetRoutineEntity1,
            routineEntity = routineEntity1
        )
        routineSetRoutineDao.insertAll(
            routineSetRoutineEntity = routineSetRoutineEntityList,
            routineEntity = routineEntityList
        )

        val result = routineSetRoutineDao.getAllRoutineSetRoutine().first()

        Truth.assertThat(result.size).isEqualTo(2)
        Truth.assertThat(result.keys.map { it.id }.toSet()).isEqualTo(
            setOf(
                routineSetRoutineEntity1.id,
                routineSetRoutineEntity2.id
            )
        )
    }

    @Test
    fun testDeleteRoutineSetRoutine() = runTest {
        routineSetRoutineDao.insertAll(
            routineSetRoutineEntity = routineSetRoutineEntityList,
            routineEntity = routineEntityList
        )
        routineSetRoutineDao.deleteRoutineSetRoutine(routineSetRoutineEntity1)
        Truth.assertThat(routineSetRoutineDao.getAllRoutineSetRoutine().first().size).isEqualTo(1)

        routineSetRoutineDao.deleteAllRoutineSetRoutine()
        Truth.assertThat(routineSetRoutineDao.getAllRoutineSetRoutine().first().size).isEqualTo(0)
    }
}