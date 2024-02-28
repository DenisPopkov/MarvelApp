package ru.popkov.marvelapp.features.main.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.popkov.marvelapp.features.main.domain.repositories.HeroRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
) : ViewModel() {
    val heroData = runBlocking { heroRepository.getHero() }
}
