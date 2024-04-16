package ru.popkov.marvelapp.features.main.ui.desc

sealed interface DescViewAction {
    data object OnBackClick : DescViewAction
}
