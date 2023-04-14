package com.gradation.lift.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class TestPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("testImplementation", libs.findLibrary("androidx-test-core").get())
                add("testImplementation", libs.findLibrary("androidx-test-espresso-core").get())
                add("androidTestImplementation", libs.findLibrary("androidx-test-rules").get())
                add("androidTestImplementation", libs.findLibrary("androidx-test-runner").get())

                add("testImplementation", libs.findLibrary("mockito-core").get())

                add("testImplementation", libs.findLibrary("robolectric").get())


                add("testImplementation", libs.findLibrary("junit4").get())
                add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
                add("androidTestImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
            }
        }
    }
}