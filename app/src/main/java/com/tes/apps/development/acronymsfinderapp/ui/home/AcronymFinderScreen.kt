package com.tes.apps.development.acronymsfinderapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun AcronymFinderScreen(
    navController: NavController,
    viewModel: AcronymViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    val acronymsState = state.acronyms

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onEvent(AcronymEvent.LoadAcronyms)
    })
    Column() {

        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(AcronymEvent.OnSearchQueryChange(it))
            },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true,
            label = { Text("Search") }
        )
        if (acronymsState.isNullOrEmpty() || state.isLoading) {
            CustomCircularProgressBar()

        } else {
            Text(
                text = "Data sf: " + acronymsState[0].sf,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                val acronyms = acronymsState[0].lfs

                items(acronyms.size) { i ->
                    val acronym = acronyms[i]
                    Text(text = "lf: " + acronym.lf)
                    Text(text = "vars: " + acronym.vars)
                    Text(text = "freq: = " + acronym.freq)
                    Text(text = "since = " + acronym.since)
                    if (i < acronyms.size) {
                        Divider(
                            modifier = Modifier.padding(
                                horizontal = 10.dp
                            )
                        )
                    }
                }
            }
        }
    }
}

//Indeterminate (Infinity)
@Composable
private fun CustomCircularProgressBar() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, bottom = 50.dp)
    ) {

        CircularProgressIndicator(
            modifier = Modifier.size(100.dp),
            color = Color.Green,
            strokeWidth = 10.dp
        )
    }
}