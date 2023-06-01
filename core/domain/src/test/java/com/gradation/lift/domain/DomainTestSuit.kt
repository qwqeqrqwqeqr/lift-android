package com.gradation.lift.domain

import com.gradation.lift.domain.usecase.DateUseCaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    DateUseCaseTest::class
)
class DomainTestSuit {

}