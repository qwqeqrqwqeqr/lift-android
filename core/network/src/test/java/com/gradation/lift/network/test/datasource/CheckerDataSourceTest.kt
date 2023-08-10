package com.gradation.lift.network.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.model.data_generator.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.CheckerDataSource
import com.gradation.lift.network.fake.FakeCheckerDataSource
import com.gradation.lift.network.utils.TestReturnState
import com.gradation.lift.model.data_generator.ModelDataGenerator.User.emailModel
import com.gradation.lift.model.data_generator.ModelDataGenerator.User.nameModel
import com.gradation.lift.test.rule.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi

class CheckerDataSourceTest {


    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: CheckerDataSource



    @Test
    fun testSignInDefaultDataSource() = runTest {
        dataSource = FakeCheckerDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            APIResult.Success(FAKE_BOOLEAN_DATA)
        ).isEqualTo(
            dataSource.checkDuplicateEmail(emailModel).first()
        )
    }

    @Test
    fun testSignUpDefaultDataSource() = runTest {
        dataSource = FakeCheckerDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            APIResult.Success(FAKE_BOOLEAN_DATA)
        ).isEqualTo(
            dataSource.checkDuplicateName(nameModel).first()
        )
    }
}