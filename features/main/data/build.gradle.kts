import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.app.feature.data)
}

val apikeyPropertiesFile = rootProject.file("apikey.properties")
val apikeyProperties = Properties()
apikeyProperties.load(FileInputStream(apikeyPropertiesFile))

android {
    namespace = "ru.popkov.marvelapp.features.main.data"
    defaultConfig {
        buildConfigField("String", "PUBLIC_KEY", apikeyProperties["PUBLIC_KEY"].toString())
        buildConfigField("String", "PRIVATE_KEY", apikeyProperties["PRIVATE_KEY"].toString())
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":features:main:domain"))
    implementation(project(":features:main:ui")) // only for accessing to resources
    implementation(libs.kotlin.coroutines)
    implementation(libs.bundles.retrofit)
}
