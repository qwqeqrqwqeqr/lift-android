package com.gradation.lift.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource.Companion.EMPTY_VALUE
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_STRING_DATA
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.inject.Inject
import javax.inject.Named


@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class UserDataStoreTest {


    @Inject
    @Named("test_datastore")
    lateinit var datastore : DataStore<Preferences>
    private lateinit var dataStoreDataSource : UserDataStoreDataSource


    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp(){
        hiltRule.inject()
        dataStoreDataSource = UserDataStoreDataSource(datastore)
    }

    @After
    fun tearDown() = runTest{
        dataStoreDataSource.clearAll()
    }


    @Test
    fun setAndGet_dataStore_outputCorrectValue() = runTest{

        dataStoreDataSource.setAccessToken(FAKE_STRING_DATA)
        dataStoreDataSource.setRefreshToken(FAKE_STRING_DATA)
        dataStoreDataSource.setUserId(FAKE_STRING_DATA)

        val accessToken = dataStoreDataSource.accessToken.first()
        val refreshToken = dataStoreDataSource.refreshToken.first()
        val userId = dataStoreDataSource.userId.first()

        assertEquals(FAKE_STRING_DATA, accessToken)
        assertEquals(FAKE_STRING_DATA, refreshToken)
        assertEquals(FAKE_STRING_DATA, userId)
    }

    @Test
    fun empty_preferences_outputCorrectValue() = runTest {
        val userId = dataStoreDataSource.userId.first()
        assertEquals(EMPTY_VALUE, userId)
    }


}