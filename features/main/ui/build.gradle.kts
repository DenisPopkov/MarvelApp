plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.marvelapp.features.main.ui"
}

dependencies {
    implementation(project(":core:feature:domain"))
    implementation(project(":features:main:nav"))
    implementation(project(":theme"))
    implementation(libs.bundles.arrow)
}
