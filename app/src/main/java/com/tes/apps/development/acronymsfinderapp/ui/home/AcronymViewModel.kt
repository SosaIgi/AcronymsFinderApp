package com.tes.apps.development.acronymsfinderapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tes.apps.development.acronymsfinderapp.data.repository.AcronymRepository
import com.tes.apps.development.acronymsfinderapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AcronymViewModel @Inject constructor(
    private val repository: AcronymRepository,
    private val mainDispatcher:CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null
    fun onEvent(event: AcronymEvent) {
        when (event) {
            is AcronymEvent.LoadAcronyms -> {
                getAcronymList()
            }
            is AcronymEvent.OnSearchQueryChange -> {
                _uiState.update { it.copy(searchQuery = event.query) }
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getAcronymList()
                }
            }
        }
    }

    fun getAcronymList(
        query: String = _uiState.value.searchQuery.lowercase()
    ) {
        viewModelScope.launch(mainDispatcher) { //was Dispatchers.main
            repository.getAcronymLists(query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                // copy of  current state so that we can change the listing
                                //_uiState.value = _uiState.value.copy(acronyms = listings)
                                // _uiState.value= UiState(acronyms = listings)
                                _uiState.update { it.copy(acronyms = listings) }
                            }
                            // _uiState.value = _uiState.value.copy()
                        }
                        is Resource.Error -> {
                            // _uiState.value = _uiState.value.copy(error = "Error message")
                            _uiState.update { it.copy(error = "Error message") }
                        }
                        is Resource.Loading -> {
                            // _uiState.value = _uiState.value.copy(isLoading = result.isLoading)
                            _uiState.update { it.copy(isLoading = result.isLoading) }
                        }
                    }
                }
        }
    }
}