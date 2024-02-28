package ru.popkov.marvelapp.features.main.ui.desc

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DescViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var heroImageId = DescDestination.Args(savedStateHandle).heroImageId
}
