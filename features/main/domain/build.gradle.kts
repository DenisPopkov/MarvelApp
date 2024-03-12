plugins {
    alias(libs.plugins.app.feature.domain)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ru.popkov.marvelapp.features.main.domain"
}

dependencies {
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlinx.serialization.json)
}
