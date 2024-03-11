/*
 * Copyright 2023 The Android Open Source Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.gradation.lift.network


import com.gradation.lift.network.test.datasource.AuthRemoteDataSourceTest
import com.gradation.lift.network.test.datasource.BadgeRemoteDataSourceTest
import com.gradation.lift.network.test.datasource.CheckerRemoteDataSourceTest
import com.gradation.lift.network.test.datasource.HistoryRemoteDataSourceTest
import com.gradation.lift.network.test.datasource.NoticeRemoteDataSourceTest
import com.gradation.lift.network.test.datasource.PictureRemoteDataSourceTest
import com.gradation.lift.network.test.datasource.RoutineRemoteDataSourceTest
import com.gradation.lift.network.test.datasource.UserRemoteDataSourceTest
import com.gradation.lift.network.test.datasource.WorkRemoteDataSourceTest
import com.gradation.lift.network.test.mapper.CreateHistoryMapperTest
import com.gradation.lift.network.test.mapper.CreateRoutineSetRoutineMapperTest
import com.gradation.lift.network.test.mapper.CreateUserBadgeMapperTest
import com.gradation.lift.network.test.mapper.CreateUserDetailMapperTest
import com.gradation.lift.network.test.mapper.ErrorMapperTest
import com.gradation.lift.network.test.mapper.SignInMapperTest
import com.gradation.lift.network.test.mapper.SignUpMapperTest
import com.gradation.lift.network.test.mapper.UpdateRoutineSetRoutineMapperTest
import com.gradation.lift.network.test.mapper.UpdateUsedRoutineSetMapperTest
import com.gradation.lift.network.test.network.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite


@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(


    AuthServiceTest::class,
    BadgeServiceTest::class,
    CheckerServiceTest::class,
    FavoriteServiceTest::class,
    RoutineServiceTest::class,
    WorkServiceTest::class,
    UserServiceTest::class,
    HistoryServiceTest::class,
    PictureServiceTest::class,
    NoticeServiceTest::class,
    RefreshServiceTest::class,
    InquiryServiceTest::class,
    TermsServiceTest::class,


    CreateHistoryMapperTest::class,
    CreateRoutineSetRoutineMapperTest::class,
    CreateUserBadgeMapperTest::class,
    CreateUserDetailMapperTest::class,
    ErrorMapperTest::class,
    SignInMapperTest::class,
    SignUpMapperTest::class,
    UpdateRoutineSetRoutineMapperTest::class,
    UpdateUsedRoutineSetMapperTest::class,


    AuthRemoteDataSourceTest::class,
    BadgeRemoteDataSourceTest::class,
    CheckerRemoteDataSourceTest::class,
    HistoryRemoteDataSourceTest::class,
    NoticeRemoteDataSourceTest::class,
    PictureRemoteDataSourceTest::class,
    RoutineRemoteDataSourceTest::class,
    UserRemoteDataSourceTest::class,
    WorkRemoteDataSourceTest::class

)
class NetworkTestSuit