package com.gradation.lift.database

import com.gradation.lift.database.test.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite


@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    BadgeDaoTest::class,
    HistoryDaoTest::class,
    PictureDaoTest::class,
    RoutineDaoTest::class,
    PictureDaoTest::class,
    WorkDaoTest::class,
    WorkPartDaoTest::class,
    WorkCategoryDaoTest::class,
    UserDaoTest::class,
    NoticeDaoTest::class,
)
class DaoTestSuite