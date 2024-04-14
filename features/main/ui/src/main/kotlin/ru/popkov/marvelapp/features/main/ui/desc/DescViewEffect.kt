package ru.popkov.marvelapp.features.main.ui.desc

internal sealed interface DescViewEffect {

    data class ShowError(val errorMessage: String) : DescViewEffect
    data object OnBackClick : DescViewEffect
}
