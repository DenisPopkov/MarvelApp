package ru.popkov.marvelapp.features.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.popkov.marvelapp.features.main.domain.repositories.HeroRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
) : ViewModel() {

    private val _heroData = MutableStateFlow(HeroModelState())
    val heroData: StateFlow<HeroModelState> = _heroData

    init {
        runBlocking { getHeroes() }
    }

    private suspend fun getHeroes() {
        viewModelScope.launch {
            val heroes = async { heroRepository.getHeroes() }
            try {
                _heroData.value = heroData.value.copy(isLoading = true)
                _heroData.value = _heroData.value.copy(
                    heroModel = heroes.await(),
                    isLoading = false
                )
            } catch (e: InternalError) {
                _heroData.value = heroData.value.copy(isLoading = false)
            }
        }
    }
}
