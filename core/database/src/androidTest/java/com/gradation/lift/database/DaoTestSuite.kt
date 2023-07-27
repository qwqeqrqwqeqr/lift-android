package com.gradation.lift.database

import com.gradation.lift.database.test.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite



@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    HistoryDaoTest::class,
    RoutineSetPictureDaoTest::class,
    RoutineSetRoutineDaoTest::class,
    UserProfilePictureDaoTest::class,
    WorkCategoryDaoTest::class,
    WorkDaoTest::class,
    WorkPartDaoTest::class,
    UserDaoTest::class,
)
class DaoTestSuite