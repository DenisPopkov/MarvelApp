package ru.popkov.marvelapp.features.main.domain.usecase

import javax.inject.Inject

class ErrorHandler @Inject constructor() {

    operator fun invoke(code: Int): String {
        return when (code) {
            APICode.NOT_FOUND.code -> APICode.NOT_FOUND.message
            else -> APICode.UNDEFINED.message
        }
    }
}
