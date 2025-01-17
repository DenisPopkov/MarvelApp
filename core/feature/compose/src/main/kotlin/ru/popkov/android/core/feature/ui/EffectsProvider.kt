package ru.popkov.android.core.feature.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface EffectsProvider<E : Any> {

    val effects: Flow<E>

    fun CoroutineScope.sendEffect(effect: E)
}
