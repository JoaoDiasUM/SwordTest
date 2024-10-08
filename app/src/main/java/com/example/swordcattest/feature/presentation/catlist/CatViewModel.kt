package com.example.swordcattest.feature.presentation.catlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swordcattest.common.Resource
import com.example.swordcattest.feature.domain.usecase.GetCats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val getCats: GetCats
) : ViewModel() {

    private val _state = MutableStateFlow(CatListScreenState())
    val state: StateFlow<CatListScreenState> = _state

    init {
        getCatsList()
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
        }.launchIn(viewModelScope)
    }
}