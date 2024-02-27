plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.compose)
    alias(libs.plugins.app.android.detekt)
}

android {
    namespace = "ru.popkov.marvelapp.theme"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}
