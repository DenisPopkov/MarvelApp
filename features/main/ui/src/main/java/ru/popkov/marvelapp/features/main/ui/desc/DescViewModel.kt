package ru.popkov.marvelapp.features.main.ui.desc

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DescViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var heroImageUrl = DescDestination.Args(savedStateHandle).heroImageUrl
    var heroNameId = DescDestination.Args(savedStateHandle).heroNameId
    var heroDescId = DescDestination.Args(savedStateHandle).heroDescId
}
