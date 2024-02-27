plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.compose)
}

android {
    namespace = "ru.popkov.android.core.feature.nav"
}

dependencies {
    implementation(libs.androidx.navigation)
}