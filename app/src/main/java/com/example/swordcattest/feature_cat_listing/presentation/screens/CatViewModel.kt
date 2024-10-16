package com.example.swordcattest.feature_cat_listing.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swordcattest.common.Resource
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import com.example.swordcattest.feature_cat_listing.domain.repository.DatabaseRepository
import com.example.swordcattest.feature_cat_listing.domain.usecase.GetCats
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
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
open class CatViewModel @Inject constructor(
    private val getCats: GetCats,
    private val databaseRepository: DatabaseRepository,
) : ViewModel() {

    // Improve this to combine all these states

    private val _state = MutableStateFlow(CatListScreenState())
    val state: StateFlow<CatListScreenState> = _state

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _cats = MutableStateFlow(listOf<CatItem>())
    @OptIn(FlowPreview::class)
    val cats = searchText
        .debounce(200L)
        .onEach { _isSearching.update { true } }
        .combine(_cats) { text, cats ->
        if (text.isBlank()) {
            cats
        } else {
            delay(300L)
            cats.filter {
                it.doesMatchSearchBreed(text)
            }
        }
    }
        .onEach { _isSearching.update { false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(2000), _cats.value)

    init {
        getCatsList()
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onFavoritesClick(updatedCat: CatItem) {
        val updatedList = _cats.value.map { cat ->
            if (cat.id == updatedCat.id) {
                updatedCat
            } else {
                cat
            }
        }

        if (updatedList != _cats.value) {
            viewModelScope.launch {
                saveCatToDB(updatedCat)
                _cats.value = updatedList
            }
        }
    }

    fun getCatById(id: String): CatItem? {
        return _cats.value.find { it.id == id }
    }

    fun getCatsList() {
        getCats.invoke().onEach { result ->
            _state.update {
                when (result) {
                    is Resource.Error ->
                        it.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false,
                            shouldShowErrorDialog = true
                        )
                    is Resource.Loading -> it.copy(isLoading = true)
                    is Resource.Success -> it.copy(
                        isLoading = false,
                    )
                }
            }
            _cats.update {
                when (result) {
                    is Resource.Error -> it
                    is Resource.Loading -> it
                    is Resource.Success -> {
                        saveAllCatsToDB(result.data)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun saveCatToDB(cat: CatItem){
        databaseRepository.insertCats(cat)
    }

    private suspend fun saveAllCatsToDB(cats: List<CatItem>?): List<CatItem> {
        cats?.let { databaseRepository.insertAllCats(it) }
        val catsFromDB = databaseRepository.getAllCats()
        return catsFromDB
    }
    @VisibleForTesting
    internal fun setCats(cats: List<CatItem>) {
        _cats.value = cats
    }
}