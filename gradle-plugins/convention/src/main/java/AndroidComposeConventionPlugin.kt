import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.popkov.android.core.gradleplugins.commonExtension
import ru.popkov.android.core.gradleplugins.configureAndroidCompose

class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            commonExtension.apply {
                configureAndroidCompose(this)
            }
        }
    }

}