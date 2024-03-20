package ru.popkov.marvelapp.features.main.ui.desc

import android.util.Log
import androidx.lifecycle.SavedStateHandle
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
internal class DescViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val heroRepository: HeroRepository,
    private val errorHandler: ErrorHandler,
) : ViewModel() {

    private var heroId = DescDestination.Args(savedStateHandle).heroId

    private val _heroData = MutableStateFlow(HeroModelState())
    val heroData: StateFlow<HeroModelState> = _heroData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        getHero()
    }

    private fun getHero() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Log.d("MarvelApp:", "error occurred: $throwable")
        }
        var hero = Pair<Error?, Hero?>(null, null)
        viewModelScope.launch(handler) {
            _heroData.value = heroData.value.copy(isLoading = true)
            hero = heroRepository.getHero(heroId = heroId ?: 0)
            _heroData.value = _heroData.value.copy(heroModel = hero.second, isLoading = false)
        }.invokeOnCompletion { error ->
            if (error != null || hero.first?.code != null) {
                viewModelScope.launch {
                    _heroData.value = _heroData.value.copy(
                        heroModel = heroRepository.getLocalHero(heroId ?: 0),
                        isLoading = false,
                    )
                    _errorMessage.value = errorHandler.invoke(hero.first?.code ?: 0)
                }
            }
        }
    }
}

