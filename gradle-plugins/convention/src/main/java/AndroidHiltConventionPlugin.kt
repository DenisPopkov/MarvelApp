import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.popkov.android.core.gradleplugins.commonExtension
import ru.popkov.android.core.gradleplugins.configureAndroidHilt

class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            commonExtension.apply {
                configureAndroidHilt(this)
            }
        }
    }

}