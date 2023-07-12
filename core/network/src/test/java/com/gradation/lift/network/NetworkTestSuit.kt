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

import com.gradation.lift.network.test.datasource.RoutineDataSourceTest
import com.gradation.lift.network.test.datasource.WorkDataSourceTest
import com.gradation.lift.network.test.network.RefreshServiceTest
import com.gradation.lift.network.test.network.RoutineServiceTest
import com.gradation.lift.network.test.network.UserServiceTest
import com.gradation.lift.network.test.network.WorkServiceTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite


@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    WorkDataSourceTest::class,
    RoutineDataSourceTest::class,

    UserServiceTest::class,
    RoutineServiceTest::class,
    WorkServiceTest::class,
    RefreshServiceTest::class
)
class NetworkTestSuit