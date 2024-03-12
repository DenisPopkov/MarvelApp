package ru.popkov.marvelapp.features.main.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.popkov.marvelapp.features.main.domain.repositories.HeroRepository
import ru.popkov.marvelapp.features.main.domain.usecase.ErrorHandler
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
    private val errorHandler: ErrorHandler,
) : ViewModel() {

    private val _heroData = MutableStateFlow(HeroModelState())
    val heroData: StateFlow<HeroModelState> = _heroData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _isInternetAvailable = MutableStateFlow(false)
    val isInternetAvailable = _isInternetAvailable.asStateFlow()

    init {
        runBlocking { getHeroes() }
    }

    private suspend fun getHeroes() {
        viewModelScope.launch {
            try {
                val heroes = async { heroRepository.getHeroes() }.await()
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
