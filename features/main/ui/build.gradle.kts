plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.marvelapp.features.main.ui"
}

dependencies {
    implementation(project(":features:main:nav"))
    implementation(project(":theme"))
}
