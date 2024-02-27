import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.popkov.android.core.gradleplugins.applicationExtension
import ru.popkov.android.core.gradleplugins.configureJUnit
import ru.popkov.android.core.gradleplugins.configureKotlin
import ru.popkov.android.core.gradleplugins.configureKotlinAndroid

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            requireNotNull(applicationExtension).apply {
                buildFeatures {
                    buildConfig = true
                }

                configureKotlin()
                configureKotlinAndroid(this)
            }

            configureJUnit()
        }
    }

}