@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "ru.popkov.android.core.gradleplugins"

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_19.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.detekt.gradlePlugin)
    compileOnly(libs.room.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
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
        register("androidHilt") {
            id = "app.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("featureDomain") {
            id = "app.feature.domain"
            implementationClass = "FeatureDomainConventionPlugin"
        }
        register("featureData") {
            id = "app.feature.data"
            implementationClass = "FeatureDataConventionPlugin"
        }
        register("androidRoom") {
            id = "app.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
    }
}
