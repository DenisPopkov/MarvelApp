import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.popkov.android.core.gradleplugins.libs

class FeatureDomainConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.run {
                apply("app.android.library")
            }

            dependencies {
                add("implementation", libs.findLibrary("javax-inject").get())
            }
        }
    }
}
