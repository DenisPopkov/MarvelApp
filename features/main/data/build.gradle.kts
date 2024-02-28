plugins {
    alias(libs.plugins.app.feature.data)
}

android {
    namespace = "ru.popkov.marvelapp.features.main.data"
}

dependencies {
    implementation(project(":features:main:domain"))
    implementation(project(":features:main:ui")) // only for accessing to resources
    implementation(libs.kotlin.coroutines)
}
