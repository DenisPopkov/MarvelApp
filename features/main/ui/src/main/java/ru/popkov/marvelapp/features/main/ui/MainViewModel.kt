package ru.popkov.marvelapp.features.main.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.popkov.marvelapp.features.main.domain.model.Hero
import ru.popkov.marvelapp.features.main.domain.repositories.HeroRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
) : ViewModel() {

    private val _heroData = MutableStateFlow(HeroModelState())
    val heroData: StateFlow<HeroModelState> = _heroData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        getHeroes()
    }

    private fun getHeroes() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Log.d("MarvelApp:", "error occurred: $throwable")
        }
        var heroes: List<Hero>?
        viewModelScope.launch(handler) {
            _heroData.value = heroData.value.copy(isLoading = true)
            heroes = heroRepository.getHeroes()
            _heroData.value = _heroData.value.copy(heroModel = heroes, isLoading = false)
        }.invokeOnCompletion { error ->
            if (error != null) {
                _heroData.value = _heroData.value.copy(heroModel = null, isLoading = false)
            }
        }
    }
}
