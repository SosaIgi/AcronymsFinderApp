package com.tes.apps.development.acronymsfinderapp.ui.home

sealed class AcronymEvent {

    object LoadAcronyms : AcronymEvent()
    data class OnSearchQueryChange(val query: String) : AcronymEvent()
}