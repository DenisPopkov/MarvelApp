plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.composesample.features.library.ui"
}

dependencies {
    implementation(project(":features:library:domain"))
    implementation(project(":features:library:nav"))
    implementation(project(":theme"))
}
