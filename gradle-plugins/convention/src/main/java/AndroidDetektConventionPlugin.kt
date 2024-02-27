import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.popkov.android.core.gradleplugins.configureDetekt
import ru.popkov.android.core.gradleplugins.detektGradle
import ru.popkov.android.core.gradleplugins.libs

class AndroidDetektConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            applyPlugins()
            detektGradle {
                configureDetekt(this)
            }
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply(libs.findLibrary("detekt-gradlePlugin").get().get().group.toString())
        }
    }
}
