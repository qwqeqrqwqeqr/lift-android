package com.gradation.lift.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class DataStorePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            //kapt 대신 ksp 사용
            with(pluginManager){
                apply("com.google.devtools.ksp")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("androidx-dataStore-core").get())
                add("implementation", libs.findLibrary("androidx-dataStore-preferences").get())

            }
        }
    }

}