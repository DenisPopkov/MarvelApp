package ru.popkov.marvelapp.features.main.ui.desc

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.popkov.marvelapp.features.main.domain.repositories.HeroRepository
import ru.popkov.marvelapp.features.main.domain.usecase.ErrorHandler
import ru.popkov.marvelapp.features.main.ui.HeroModelState
import java.io.IOException
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
        viewModelScope.launch {
            try {
                val heroes = withContext(Dispatchers.IO) {
                    _heroData.value = heroData.value.copy(isLoading = true)
                    heroRepository.getHero(heroId ?: 0)
                }
                _errorMessage.update { errorHandler.invoke(heroes.code) }
                _heroData.value = _heroData.value.copy(
                    heroModel = heroes,
                    isLoading = false
                )
            } catch (e: IOException) {
                // Handle any potential exceptions gracefully
                _heroData.value = heroData.value.copy(isLoading = false)
            }
        }
    }
}

