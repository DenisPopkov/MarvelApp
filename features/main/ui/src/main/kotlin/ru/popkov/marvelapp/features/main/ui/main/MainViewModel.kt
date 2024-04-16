package ru.popkov.marvelapp.features.main.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import ru.popkov.marvelapp.features.main.domain.repositories.HeroRepository
import ru.popkov.marvelapp.features.main.domain.usecase.ErrorHandler
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
    private val errorHandler: ErrorHandler,
) : ViewModel(),
    StateProvider<MainState> by StateDelegate(MainState()),
    EffectsProvider<MainViewEffect> by EffectsDelegate() {

    fun onAction(action: MainViewAction) {
        when (action) {
            is MainViewAction.OnHeroClick -> {
                viewModelScope.launch {
                    sendEffect(MainViewEffect.GoToDescriptionScreen(action.heroId))
                }
            }
        }
    }

    fun getHeroes() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            when (val heroes = heroRepository.getHeroes()) {
                is Either.Left -> {
                    updateState { copy(isLoading = false) }
                    sendEffect(MainViewEffect.ShowError(errorHandler.invoke(heroes.value.code)))
                }

                is Either.Right -> updateState { copy(isLoading = false) }
            }
        }
    }

    fun getLocalHeroes() = heroRepository.getLocalHeroes()
}
