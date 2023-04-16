package com.gradation.lift.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.gradation.lift.plugin.extension.extensionAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidComposeApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            with(pluginManager) {
                apply(libs.findPlugin("android-application").get().get().pluginId)
            }
            val extension = extensions.getByType<ApplicationExtension>()
            extensionAndroidCompose(extension)
        }
    }

}