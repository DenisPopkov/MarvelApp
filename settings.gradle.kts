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
    ":core:feature:compose",
    ":features:main:nav",
    ":features:main:ui",
    ":features:main:domain",
    ":features:main:data",
    ":theme",
)
