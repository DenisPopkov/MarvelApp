plugins {
    alias(libs.plugins.app.feature.domain)
}

android {
    namespace = "ru.popkov.marvelapp.features.main.domain"
}

dependencies {
    implementation(libs.kotlin.coroutines)
}
