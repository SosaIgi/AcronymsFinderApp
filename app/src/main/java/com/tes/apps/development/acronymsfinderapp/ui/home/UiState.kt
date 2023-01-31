package com.tes.apps.development.acronymsfinderapp.ui.home

import com.tes.apps.development.acronymsfinderapp.data.remote.AcronymDtoItem

data class UiState(
    val acronyms: List<AcronymDtoItem> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "Ne",
    val acronymItem: AcronymDtoItem = AcronymDtoItem(lfs = emptyList(), sf = ""),
    val error: String = ""
)