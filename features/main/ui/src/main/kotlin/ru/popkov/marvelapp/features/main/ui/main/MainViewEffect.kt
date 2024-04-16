package ru.popkov.marvelapp.features.main.ui.main

internal sealed interface MainViewEffect {

    data class ShowError(val errorMessage: String) : MainViewEffect
    data class GoToDescriptionScreen(val heroId: Int) : MainViewEffect
}
