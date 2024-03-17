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
import ru.popkov.marvelapp.features.main.domain.model.HeroData
import ru.popkov.marvelapp.features.main.domain.repositories.HeroRepository
import ru.popkov.marvelapp.features.main.domain.usecase.ErrorHandler
import ru.popkov.marvelapp.features.main.ui.HeroModelState
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
        var heroes: HeroData? = null
        viewModelScope.launch(handler) {
            _heroData.value = heroData.value.copy(isLoading = true)
            heroes = heroRepository.getHero(characterId = heroId ?: 0)
            _heroData.value = _heroData.value.copy(heroModel = heroes, isLoading = false)
        }.invokeOnCompletion { error ->
            if (error != null || heroes?.code != ErrorHandler.APICode.GOOD.code) {
                _heroData.value = _heroData.value.copy(heroModel = null, isLoading = false)
                _errorMessage.value = errorHandler.invoke(heroes?.code ?: 0)
            }
        }
    }
}

