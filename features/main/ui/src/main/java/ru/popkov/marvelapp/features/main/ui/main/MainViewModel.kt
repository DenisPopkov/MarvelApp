package ru.popkov.marvelapp.features.main.ui.main

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
import ru.popkov.marvelapp.features.main.domain.repositories.Error
import ru.popkov.marvelapp.features.main.domain.repositories.HeroRepository
import ru.popkov.marvelapp.features.main.domain.usecase.ErrorHandler
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
    private val errorHandler: ErrorHandler,
) : ViewModel() {

    private val _heroData = MutableStateFlow(HeroesModelState())
    val heroData: StateFlow<HeroesModelState> = _heroData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        getHeroes()
    }

    private fun getHeroes() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Log.d("MarvelApp:", "error occurred: $throwable")
        }
        var heroes = Pair<Error?, List<Hero>?>(null, null)
        viewModelScope.launch(handler) {
            _heroData.value = heroData.value.copy(isLoading = true)
            heroes = heroRepository.getHeroes()
            _heroData.value = _heroData.value.copy(heroModel = heroes.second, isLoading = false)
        }.invokeOnCompletion { error ->
            if (error != null || heroes.first?.code != null) {
                viewModelScope.launch {
                    _heroData.value = _heroData.value.copy(heroModel = heroRepository.getLocalHeroes(), isLoading = false)
                    _errorMessage.value = errorHandler.invoke(heroes.first?.code ?: 0)
                }
            }
        }
    }
}
