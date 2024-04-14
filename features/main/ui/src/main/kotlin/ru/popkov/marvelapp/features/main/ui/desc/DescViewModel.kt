package ru.popkov.marvelapp.features.main.ui.desc

import androidx.lifecycle.SavedStateHandle
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
internal class DescViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val heroRepository: HeroRepository,
    private val errorHandler: ErrorHandler,
) : ViewModel(),
    StateProvider<DescState> by StateDelegate(DescState()),
    EffectsProvider<DescViewEffect> by EffectsDelegate() {

    private var heroId = DescDestination.Args(savedStateHandle).heroId

    fun onAction(action: DescViewAction) {
        when (action) {
            is DescViewAction.OnBackClick -> {
                viewModelScope.launch {
                    sendEffect(DescViewEffect.OnBackClick)
                }
            }
        }
    }

    fun getHero() {
        viewModelScope.launch {
            val hero = heroRepository.getHero(heroId ?: 0)
            updateState { copy(isLoading = true) }
            when (hero) {
                is Either.Left -> {
                    val localHero = heroRepository.getLocalHero(heroId ?: 0)
                    updateState { copy(heroModel = localHero, isLoading = false) }
                    sendEffect(DescViewEffect.ShowError(errorHandler.invoke(hero.value.code)))
                }

                is Either.Right -> {
                    updateState { copy(heroModel = hero.getOrNull(), isLoading = false) }
                }
            }
        }
    }
}
