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


import com.gradation.lift.network.test.datasource.AuthDataSourceTest
import com.gradation.lift.network.test.datasource.BadgeDataSourceTest
import com.gradation.lift.network.test.datasource.CheckerDataSourceTest
import com.gradation.lift.network.test.datasource.HistoryDataSourceTest
import com.gradation.lift.network.test.datasource.NoticeDataSourceTest
import com.gradation.lift.network.test.datasource.PictureDataSourceTest
import com.gradation.lift.network.test.datasource.RoutineDataSourceTest
import com.gradation.lift.network.test.datasource.UserDataSourceTest
import com.gradation.lift.network.test.datasource.WorkDataSourceTest
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


    AuthDataSourceTest::class,
    BadgeDataSourceTest::class,
    CheckerDataSourceTest::class,
    HistoryDataSourceTest::class,
    NoticeDataSourceTest::class,
    PictureDataSourceTest::class,
    RoutineDataSourceTest::class,
    UserDataSourceTest::class,
    WorkDataSourceTest::class

)
class NetworkTestSuit