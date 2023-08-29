package com.gradation.lift.data

import com.gradation.lift.data.datasource.AuthDataSourceTest
import com.gradation.lift.data.datasource.CheckerDataSourceTest
import com.gradation.lift.data.datasource.HistoryDataSourceTest
import com.gradation.lift.data.datasource.PictureDataSourceTest
import com.gradation.lift.data.datasource.RoutineDataSourceTest
import com.gradation.lift.data.datasource.UserDataSourceTest
import com.gradation.lift.data.datasource.WorkDataSourceTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    AuthDataSourceTest::class,
    CheckerDataSourceTest::class,
    UserDataSourceTest::class,
    RoutineDataSourceTest::class,
    WorkDataSourceTest::class,
    PictureDataSourceTest::class,
    HistoryDataSourceTest::class,
)
class DataTestSuit