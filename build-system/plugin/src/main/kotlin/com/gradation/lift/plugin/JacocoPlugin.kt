package com.gradation.lift.plugin

import com.gradation.lift.plugin.extension.extensionJacoco
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension

class JacocoPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            with(pluginManager) {
                apply(libs.findPlugin("jacoco").get().get().pluginId)
            }
            extensions.configure<JacocoPluginExtension> {
                extensionJacoco(this)
            }
        }
    }
}