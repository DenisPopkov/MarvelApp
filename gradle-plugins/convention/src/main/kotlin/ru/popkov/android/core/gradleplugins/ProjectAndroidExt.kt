package ru.popkov.android.core.gradleplugins

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.api.plugins.PluginInstantiationException
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.findByType

val Project.commonExtension: CommonExtension<*, *, *, *, *, *>
    get() = applicationExtension
        ?: libraryExtension
        ?: throw PluginInstantiationException("Can only be applied on an android Application or Library")

val Project.applicationExtension: ApplicationExtension?
    get() = extensions.findByType<ApplicationExtension>()

val Project.libraryExtension: LibraryExtension?
    get() = extensions.findByType<LibraryExtension>()

inline fun Project.detektGradle(crossinline configure: DetektExtension.() -> Unit) =
    extensions.configure<DetektExtension> {
        configure()
    }

inline fun Project.androidGradle(crossinline configure: com.android.build.gradle.LibraryExtension.() -> Unit) =
    extensions.configure<com.android.build.gradle.LibraryExtension> {
        configure()
    }
