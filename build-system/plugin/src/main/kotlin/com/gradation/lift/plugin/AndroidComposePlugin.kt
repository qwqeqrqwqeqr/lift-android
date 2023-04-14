package com.gradation.lift.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.gradation.lift.plugin.extension.extensionAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val extension = extensions.getByType<ApplicationExtension>()
            extensionAndroidCompose(extension)

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                val bom = libs.findLibrary("androidx-compose-bom").get()
                add("implementation", platform(bom))
                add("androidTestImplementation", platform(bom))


                add("implementation", libs.findLibrary("androidx-activity-compose").get())
                add("implementation", libs.findLibrary("androidx-hilt-navigation-compose").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-runtime-compose").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())

                add("implementation", libs.findLibrary("androidx.compose.ui").get())
                add("implementation", libs.findLibrary("androidx-compose-ui-tooling").get())
                add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
                add("implementation", libs.findLibrary("androidx-compose-runtime").get())
                add("implementation", libs.findLibrary("androidx-compose-runtime-livedata").get())


                add("implementation", libs.findLibrary("androidx-compose-foundation").get())
                add("implementation", libs.findLibrary("androidx-compose-foundation-layout").get())
                add("implementation", libs.findLibrary("androidx-compose-material-icons-core").get())
                add("implementation", libs.findLibrary("androidx-compose-material-icons-extended").get())
                add("implementation", libs.findLibrary("androidx-compose-material3").get())
                add("implementation", libs.findLibrary("androidx-compose-material3-windowsizeclass").get())


                add("implementation", libs.findLibrary("androidx-compose-ui-test").get())
                add("implementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())
            }
        }
    }

//    api(libs.androidx.compose.foundation)
//    api(libs.androidx.compose.foundation.layout)
//    api(libs.androidx.compose.material.iconsExtended)
//    api(libs.androidx.compose.material3)
//    debugApi(libs.androidx.compose.ui.tooling)
//    api(libs.androidx.compose.ui.tooling.preview)
//    api(libs.androidx.compose.ui.util)
//    api(libs.androidx.compose.runtime)
}