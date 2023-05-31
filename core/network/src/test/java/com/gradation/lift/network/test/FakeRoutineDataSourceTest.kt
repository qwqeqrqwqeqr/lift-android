package com.gradation.lift.network.test

import com.gradation.lift.network.datasource.WorkDataSource
import com.gradation.lift.test.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule


@ExperimentalCoroutinesApi
class FakeRoutineDataSourceTest {

    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: WorkDataSource
    @Before
    fun tearUp() {
    }

    @After
    fun tearDown() {}
}