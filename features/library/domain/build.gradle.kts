plugins {
    alias(libs.plugins.app.feature.domain)
}

android {
    namespace = "ru.popkov.composesample.features.library.domain"
}

dependencies {
    implementation(libs.kotlin.coroutines)
}
