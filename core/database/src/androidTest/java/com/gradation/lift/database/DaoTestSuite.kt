package com.gradation.lift.database

import com.gradation.lift.database.test.RoutineSetDaoTest
import com.gradation.lift.database.test.WorkCategoryDaoTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite



@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(WorkCategoryDaoTest::class,RoutineSetDaoTest::class)
class DaoTestSuite