package ru.popkov.marvelapp.features.main.ui.main

import android.util.Log
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
class MainViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
    private val errorHandler: ErrorHandler,
) : ViewModel(),
    StateProvider<MainState> by StateDelegate(MainState()),
    EffectsProvider<MainViewEffect> by EffectsDelegate() {

    init {
        getHeroes()
    }

    fun onAction(action: MainViewAction) {
        when (action) {
            is MainViewAction.OnHeroClick -> {
                viewModelScope.launch {
                    sendEffect(MainViewEffect.GoToDescriptionScreen(action.heroId))
                }
            }
        }
    }

    private fun getHeroes() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Log.d("MarvelApp:", "error occurred: $throwable")
        }
        var heroes = Pair<Error?, List<Hero>?>(null, null)
        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            heroes = heroRepository.getHeroes()
            updateState { copy(heroModel = heroes.second, isLoading = false) }
        }.invokeOnCompletion { error ->
            if (error != null || heroes.first?.code != null) {
                viewModelScope.launch {
                    val localHeroes = heroRepository.getLocalHeroes()
                    updateState { copy(heroModel = localHeroes, isLoading = false) }
                    sendEffect(MainViewEffect.ShowError(errorHandler.invoke(heroes.first?.code ?: 0) ?: ""))
                }
            }
        }
    }
}
