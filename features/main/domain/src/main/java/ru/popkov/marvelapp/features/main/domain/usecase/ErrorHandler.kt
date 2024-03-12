package ru.popkov.marvelapp.features.main.domain.usecase

import javax.inject.Inject

class ErrorHandler @Inject constructor() {
    enum class APICode(val code: String) {
        NOT_FOUND(code = "404"),
        GOOD(code = "200")
    }

    enum class ErrorText(val message: String) {
        NOT_FOUND(message = "Герой не найден"),
        UNDEFINED(message = "Неизвестная ошибка")
    }

    operator fun invoke(error: Int): String? {
        return when (error.toString()) {
            APICode.GOOD.code -> null
            APICode.NOT_FOUND.code -> ErrorText.NOT_FOUND.message
            else -> ErrorText.UNDEFINED.message
        }
    }
}
