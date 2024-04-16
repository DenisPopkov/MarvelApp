import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.app.feature.data)
    alias(libs.plugins.app.android.room)
}

val apikeyPropertiesFile = rootProject.file("apikey.properties")
val apikeyProperties = Properties()
apikeyProperties.load(FileInputStream(apikeyPropertiesFile))

android {
    namespace = "ru.popkov.android.core.feature.data"
    defaultConfig {
        buildConfigField("String", "PUBLIC_KEY", apikeyProperties["PUBLIC_KEY"].toString())
        buildConfigField("String", "PRIVATE_KEY", apikeyProperties["PRIVATE_KEY"].toString())
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:feature:domain"))
    implementation(libs.kotlin.coroutines)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.arrow)
}
