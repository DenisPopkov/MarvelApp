package ru.popkov.marvelapp.features.main.domain.usecase

@JvmInline
value class ErrorCode(val code: Int)

enum class APICode(val code: Int, val message: String) {
    NOT_FOUND(code = 404, message = "Герой не найден"),
    UNDEFINED(code = 500, message = "Нет интернета")
}
