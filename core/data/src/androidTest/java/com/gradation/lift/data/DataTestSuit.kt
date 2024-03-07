package com.gradation.lift.data

import com.gradation.lift.data.test.datasource.AuthRemoteDataSourceTest
import com.gradation.lift.data.test.datasource.CheckerRemoteDataSourceTest
import com.gradation.lift.data.test.datasource.HistoryRemoteDataSourceTest
import com.gradation.lift.data.test.datasource.PictureRemoteDataSourceTest
import com.gradation.lift.data.test.datasource.RoutineRemoteDataSourceTest
import com.gradation.lift.data.test.datasource.UserRemoteDataSourceTest
import com.gradation.lift.data.test.datasource.WorkRemoteDataSourceTest
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
    AuthRemoteDataSourceTest::class,
    CheckerRemoteDataSourceTest::class,
    UserRemoteDataSourceTest::class,
    RoutineRemoteDataSourceTest::class,
    WorkRemoteDataSourceTest::class,
    PictureRemoteDataSourceTest::class,
    HistoryRemoteDataSourceTest::class,


    AuthRepositoryTest::class,
    CheckerRepositoryTest::class,
    HistoryRepositoryTest::class,
    PictureRepositoryTest::class,
    RoutineRepositoryTest::class,
    UserRepositoryTest::class,


)
class DataTestSuit