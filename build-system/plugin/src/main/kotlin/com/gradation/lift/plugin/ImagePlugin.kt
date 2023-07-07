package com.gradation.lift.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class ImagePlugin  : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")


            dependencies {
                add("implementation", libs.findLibrary("glide").get())
                add("annotationProcessor", libs.findLibrary("glide-annotation-processor").get())

            }
        }
    }

}