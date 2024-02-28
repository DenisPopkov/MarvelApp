plugins {
    alias(libs.plugins.app.android.application)
    alias(libs.plugins.app.android.compose)
    alias(libs.plugins.app.android.detekt)
    alias(libs.plugins.app.android.hilt)
}

android {
    namespace = "ru.popkov.marvelapp"

    defaultConfig {
        applicationId = "ru.popkov.marvelapp"
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":theme"))
    implementation(project(":features:main:ui"))
    implementation(project(":features:main:data"))
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}
