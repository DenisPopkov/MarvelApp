package ru.popkov.marvelapp.features.main.ui.desc

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
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
) : ViewModel(),
    StateProvider<DescState> by StateDelegate(DescState()),
    EffectsProvider<DescViewEffect> by EffectsDelegate() {

    private var heroId = DescDestination.Args(savedStateHandle).heroId

    init {
        getHero()
    }

    fun onAction(action: DescViewAction) {
        when (action) {
            is DescViewAction.OnBackClick -> {
                viewModelScope.launch {
                    sendEffect(DescViewEffect.OnBackClick)
                }
            }
        }
    }

    private fun getHero() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Log.d("MarvelApp:", "error occurred: $throwable")
        }
        var hero = Pair<Error?, Hero?>(null, null)
        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            hero = heroRepository.getHero(heroId ?: 0)
            updateState { copy(heroModel = hero.second, isLoading = false) }
        }.invokeOnCompletion { error ->
            if (error != null || hero.first?.code != null) {
                viewModelScope.launch {
                    val localHero = heroRepository.getLocalHero(heroId ?: 0)
                    updateState { copy(heroModel = localHero, isLoading = false) }
                    sendEffect(DescViewEffect.ShowError(errorHandler.invoke(hero.first?.code ?: 0) ?: ""))
                }
            }
        }
    }
}
