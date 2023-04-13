package com.gradation.lift.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.gradation.lift.plugin.extension.extensionAndroid
import com.gradation.lift.plugin.extension.extensionKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class KotlinPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            with(pluginManager) {
                apply(libs.findPlugin("kotlin-jvm").get().get().pluginId)
                apply(libs.findPlugin("kotlin-serialization").get().get().pluginId)
                apply(libs.findPlugin("ksp").get().get().pluginId)
            }

            extensions.configure<ApplicationExtension> {
                extensionKotlin(this)
            }

            dependencies {
                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
                add("implementation", libs.findLibrary("kotlin-stdlib").get())
                add("implementation", libs.findLibrary("kotlinx-serialization-json").get())
            }
        }
    }
}