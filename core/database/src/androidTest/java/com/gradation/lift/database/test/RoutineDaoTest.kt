package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.LiftDatabase
import com.gradation.lift.database.dao.RoutineDao
import com.gradation.lift.database.data.TestDataGenerator.Routine.ROUTINE_ENTITY
import com.gradation.lift.database.data.TestDataGenerator.Routine.ROUTINE_SET_ROUTINE_ENTITY
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
class RoutineDaoTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var routineDao: RoutineDao

    @Before
    fun setup() {
        hiltRule.inject()
        routineDao = database.routineDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testHistoryInsertAndDelete() = runTest {

        with(routineDao.getAllRoutineSetRoutine().first()) {
            Truth.assertThat(this.size).isEqualTo(0)
        }

        /**
         * insert item
         */
        routineDao.insertAll(
            routineSetRoutineEntity = listOf(ROUTINE_SET_ROUTINE_ENTITY),
            routineEntity = listOf(ROUTINE_ENTITY)
        )

        with(routineDao.getAllRoutineSetRoutine().first()) {
            Truth.assertThat(this.keys.size).isEqualTo(1)
            Truth.assertThat(this.values.size).isEqualTo(1)

            with(this.entries.first().key) {
                Truth.assertThat(this.id).isEqualTo(ROUTINE_SET_ROUTINE_ENTITY.id)
                Truth.assertThat(this.count).isEqualTo(ROUTINE_SET_ROUTINE_ENTITY.count)
                Truth.assertThat(this.description).isEqualTo(ROUTINE_SET_ROUTINE_ENTITY.description)
                Truth.assertThat(this.name).isEqualTo(ROUTINE_SET_ROUTINE_ENTITY.name)
                Truth.assertThat(this.label).isEqualTo(ROUTINE_SET_ROUTINE_ENTITY.label)
                Truth.assertThat(this.picture).isEqualTo(ROUTINE_SET_ROUTINE_ENTITY.picture)
                Truth.assertThat(this.weekday).isEqualTo(ROUTINE_SET_ROUTINE_ENTITY.weekday)
            }

            with(this.entries.first().value.first()) {
                Truth.assertThat(this.id).isEqualTo(ROUTINE_ENTITY.id)
                Truth.assertThat(this.routineSetId).isEqualTo(ROUTINE_ENTITY.routineSetId)
                Truth.assertThat(this.workPart).isEqualTo(ROUTINE_ENTITY.workPart)
                Truth.assertThat(this.workCategoryId).isEqualTo(ROUTINE_ENTITY.workCategoryId)
                Truth.assertThat(this.workCategoryName).isEqualTo(ROUTINE_ENTITY.workCategoryName)
                Truth.assertThat(this.workSetList).isEqualTo(ROUTINE_ENTITY.workSetList)
            }
        }

        /**
         * delete item
         */
        routineDao.deleteAllRoutineSetRoutine()


        with(routineDao.getAllRoutineSetRoutine().first()) {
            Truth.assertThat(this.size).isEqualTo(0)
        }
    }


}