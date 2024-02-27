import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import ru.popkov.android.core.gradleplugins.androidGradle
import ru.popkov.android.core.gradleplugins.configureJUnit
import ru.popkov.android.core.gradleplugins.configureKotlin
import ru.popkov.android.core.gradleplugins.configureKotlinAndroid

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            applyPlugins()
            applyDependencies()
            androidGradle {
                configureKotlinAndroid(this)
                configureJUnit()
                configureKotlin()
            }
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
            apply("app.android.detekt")
        }
    }

    private fun Project.applyDependencies() {
        dependencies {
            "androidTestImplementation"(kotlin("test"))
            "testImplementation"(kotlin("test"))
        }
    }
}
