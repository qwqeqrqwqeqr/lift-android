package com.gradation.lift.database

import com.gradation.lift.database.test.*
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    BadgeDaoTest::class,
    HistoryDaoTest::class,
    RoutineDaoTest::class,
    UserBadgeDaoTest::class,
    WorkCategoryDaoTest::class,
    WorkDaoTest::class,
    WorkPartDaoTest::class,
)
class DaoTestSuite