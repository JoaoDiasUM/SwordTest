package com.example.swordcattest.feature.presentation.catlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swordcattest.common.Resource
import com.example.swordcattest.feature.domain.model.CatItem
import com.example.swordcattest.feature.domain.usecase.GetCats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val getCats: GetCats
) : ViewModel() {

    private val _state = MutableStateFlow(CatListScreenState())
    val state: StateFlow<CatListScreenState> = _state

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _cats = MutableStateFlow(listOf<CatItem>())
    @OptIn(FlowPreview::class)
    val cats = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_cats) { text, cats ->
        if (text.isBlank()) {
            cats
        } else {
            delay(2000L)
            cats.filter {
                it.doesMatchSearchBreed(text)
            }
        }
    }
        .onEach { _isSearching.update { false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _cats.value)

    init {
        getCatsList()
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private fun getCatsList() {
        getCats.invoke().onEach { result ->
            _state.update {
                when (result) {
                    is Resource.Error ->
                        it.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )

                    is Resource.Loading -> it.copy(isLoading = true)
                    is Resource.Success -> {
                        it.copy(
                            catList = result.data ?: emptyList(),
                        )
                    }
                }
            }
            _cats.update {
                when (result) {
                    is Resource.Error -> it
                    is Resource.Loading -> it
                    is Resource.Success -> {
                        result.data ?: emptyList()
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}