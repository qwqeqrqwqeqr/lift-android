package com.gradation.lift.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class NetworkPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                val bom = libs.findLibrary("okhttp-bom").get()
                add("implementation", platform(bom))

                add("implementation", libs.findLibrary("okhttp").get())
                add("implementation", libs.findLibrary("okhttp-logging-interceptor").get())

                add("implementation", libs.findLibrary("retrofit-core").get())
                add("implementation", libs.findLibrary("retrofit-kotlin-serialization").get())
                add("implementation", libs.findLibrary("retrofit-converter-moshi").get())
                add("implementation", libs.findLibrary("moshi").get())
                add("implementation", libs.findLibrary("moshi-kotlin").get())

                add("testImplementation", libs.findLibrary("okhttp-mockwebserver").get())
                add("androidTestImplementation", libs.findLibrary("okhttp-mockwebserver").get())

            }
        }


    }
}