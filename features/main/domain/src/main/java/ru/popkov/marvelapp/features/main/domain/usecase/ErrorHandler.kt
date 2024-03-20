package ru.popkov.marvelapp.features.main.domain.usecase

import javax.inject.Inject

class ErrorHandler @Inject constructor() {

    enum class APICode(val code: Int, val message: String) {
        NOT_FOUND(code = 404, message = "Герой не найден"),
        GOOD(code = 200, "OK"),
        UNDEFINED(code = 500, message = "Нет интернета")
    }

    operator fun invoke(code: Int): String? {
        return when (code) {
            APICode.GOOD.code -> null
            APICode.NOT_FOUND.code -> APICode.NOT_FOUND.message
            else -> APICode.UNDEFINED.message
        }
    }
}
