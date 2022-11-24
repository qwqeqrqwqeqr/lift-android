package com.gradation.lift.database

import com.gradation.lift.database.data.RoutineSetDaoTest
import com.gradation.lift.database.data.WorkCategoryDaoTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite



@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(RoutineSetDaoTest::class)
class DaoTestSuite