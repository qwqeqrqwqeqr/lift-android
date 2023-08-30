package com.gradation.lift.data

import com.gradation.lift.data.test.datasource.AuthDataSourceTest
import com.gradation.lift.data.test.datasource.CheckerDataSourceTest
import com.gradation.lift.data.test.datasource.HistoryDataSourceTest
import com.gradation.lift.data.test.datasource.PictureDataSourceTest
import com.gradation.lift.data.test.datasource.RoutineDataSourceTest
import com.gradation.lift.data.test.datasource.UserDataSourceTest
import com.gradation.lift.data.test.datasource.WorkDataSourceTest
import com.gradation.lift.data.test.repository.AuthRepositoryTest
import com.gradation.lift.data.test.repository.CheckerRepositoryTest
import com.gradation.lift.data.test.repository.HistoryRepositoryTest
import com.gradation.lift.data.test.repository.PictureRepositoryTest
import com.gradation.lift.data.test.repository.RoutineRepositoryTest
import com.gradation.lift.data.test.repository.UserRepositoryTest
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


    AuthRepositoryTest::class,
    CheckerRepositoryTest::class,
    HistoryRepositoryTest::class,
    PictureRepositoryTest::class,
    RoutineRepositoryTest::class,
    UserRepositoryTest::class,


)
class DataTestSuit