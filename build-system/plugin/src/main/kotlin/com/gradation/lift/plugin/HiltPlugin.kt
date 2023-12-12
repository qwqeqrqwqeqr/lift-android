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
                apply(libs.findPlugin("ksp").get().get().pluginId)
            }

            dependencies {
                add("implementation", libs.findLibrary("hilt.android").get())
                add("ksp", libs.findLibrary("hilt.compiler").get())
                add("implementation", libs.findLibrary("hilt-android-testing").get())
                add("implementation", libs.findLibrary("hilt-work").get())
                add("implementation", libs.findLibrary("hilt-navigation-compose").get())
                add("androidTestImplementation", libs.findLibrary("hilt-android-testing").get())
            }
        }
    }
}
