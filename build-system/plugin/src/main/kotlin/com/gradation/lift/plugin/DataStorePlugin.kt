package com.gradation.lift.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class DataStorePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            with(pluginManager){
                apply(libs.findPlugin("ksp").get().get().pluginId)
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx-datastore-preferences-core").get())
                add("implementation", libs.findLibrary("androidx-datastore-preferences").get())
                add("implementation", libs.findLibrary("androidx-datastore-core").get())
                add("implementation", libs.findLibrary("androidx-datastore").get())

            }
        }
    }

}