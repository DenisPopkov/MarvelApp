@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
}

group = "ru.popkov.android.core.gradleplugins"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.jvm.get()))
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.detekt.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "app.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "app.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = "app.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("featureUi") {
            id = "app.feature.ui"
            implementationClass = "FeatureUiConventionPlugin"
        }
        register("androidDetekt") {
            id = "app.android.detekt"
            implementationClass = "AndroidDetektConventionPlugin"
        }
        register("featureNav") {
            id = "app.feature.nav"
            implementationClass = "FeatureNavConventionPlugin"
        }
    }
}
