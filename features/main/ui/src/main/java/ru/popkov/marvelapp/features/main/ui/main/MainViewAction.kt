package ru.popkov.marvelapp.features.main.ui.main

sealed interface MainViewAction {
    data class OnHeroClick(val heroId: Int) : MainViewAction
}
