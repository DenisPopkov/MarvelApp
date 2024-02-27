plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.composesample.features.spotlight.ui"
}

dependencies {
    implementation(project(":features:library:domain"))
    implementation(project(":features:library:nav"))
    implementation(project(":features:spotlight:nav"))
    implementation(project(":theme"))
}