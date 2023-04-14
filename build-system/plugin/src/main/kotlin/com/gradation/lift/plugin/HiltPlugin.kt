package com.gradation.lift.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class HiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            with(pluginManager) {
                apply(libs.findPlugin("hilt").get().get().pluginId)
                //TODO https://github.com/google/dagger/issues/2349
                apply(libs.findPlugin("kapt").get().get().pluginId)
            }

            dependencies {
                add("implementation", libs.findLibrary("hilt.android").get())
                add("kapt", libs.findLibrary("hilt.compiler").get())


                add("implementation", libs.findLibrary("hilt-android-testing").get())
                add("kaptTest", libs.findLibrary("hilt-compiler").get())
                add("kaptAndroidTest", libs.findLibrary("hilt-compiler").get())
            }

        }
    }

}