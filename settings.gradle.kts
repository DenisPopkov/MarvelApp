@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("gradle-plugins")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MarvelApp"
include(
    ":app",
    ":core:feature:nav",
    ":core:feature:ui",
    ":features:main:nav",
    ":features:main:ui",
    ":theme",
)
