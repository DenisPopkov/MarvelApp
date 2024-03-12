package ru.popkov.marvelapp.features.main.ui.desc

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    private var heroId = DescDestination.Args(savedStateHandle).heroId.toString()
    private val correctedHeroId = heroId.replace("{", "").replace("}", "")

    private val _heroData = MutableStateFlow(HeroModelState())
    val heroData: StateFlow<HeroModelState> = _heroData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun getHero() {
        viewModelScope.launch {
            try {
                val heroes = async { heroRepository.getHero(correctedHeroId.toInt()) }.await()
                _errorMessage.update { errorHandler.invoke(heroes.code) }
                _heroData.value = heroData.value.copy(isLoading = true)
                _heroData.value = _heroData.value.copy(
                    heroModel = heroes,
                    isLoading = false
                )
            } catch (e: InternalError) {
                _heroData.value = heroData.value.copy(isLoading = false)
            }
        }
    }
}
