package com.tes.apps.development.acronymsfinderapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.tes.apps.development.acronymsfinderapp.data.remote.AcronymApiService
import com.tes.apps.development.acronymsfinderapp.data.repository.AcronymRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class AcronymViewModelTest {

    @get:Rule
    val instantTaskExecutionRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: AcronymRepository

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: AcronymViewModel


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)
        viewModel = AcronymViewModel(repository, testDispatcher)
    }

    @Test
    fun `check viewModel initial state`() {
        runTest {
            val expectedState = UiState()
            val updatedState = viewModel.uiState.value
            assertEquals(expectedState, updatedState)
        }
    }

    @Test
    fun `check viewModel updated state`() {
        runTest {
            val expectedState = UiState(isLoading = false, searchQuery = "He")
            viewModel.onEvent(AcronymEvent.OnSearchQueryChange("He"))
            // Await the change
            testDispatcher.scheduler.advanceUntilIdle()
            val updatedState = viewModel.uiState.value
            assertEquals(expectedState, updatedState)
        }
    }

    @Test
    fun `Success state updates value `() {
        runTest {
            val expectedState = UiState(isLoading = false, searchQuery = "Good")
            viewModel.onEvent(AcronymEvent.OnSearchQueryChange("Good"))
            viewModel.uiState.test {
                assertEquals( expectedState, awaitItem()) //emit  data
            }
        }
    }

    @After
    fun tearDown() {
    }
}