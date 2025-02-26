package com.plcoding.androidinternals2.dictionary

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DictionaryViewModel(
    private val dictionaryProvider: DictionaryProviderClient
): ViewModel() {

    private var searchJob: Job? = null

    var searchQuery by mutableStateOf("")
        private set

    var words by mutableStateOf(emptyList<Word>())
        private set

    private var cachedWords = emptyList<Word>()

    init {
        viewModelScope.launch {
            words = dictionaryProvider.queryWords()
            cachedWords = words
        }
    }

    fun onSearchQueryChange(query: String) {
        searchQuery = query
        searchJob?.cancel()

        if(query.isBlank()) {
            words = cachedWords
            return
        }

        searchJob = viewModelScope.launch {
            delay(500L)
            words = dictionaryProvider.queryWords(searchFor = query)
        }
    }
}