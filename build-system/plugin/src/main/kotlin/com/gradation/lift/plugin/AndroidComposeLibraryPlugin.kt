package com.gradation.lift.plugin

import com.android.build.api.dsl.LibraryExtension
import com.gradation.lift.plugin.extension.extensionAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidComposeLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val extension = extensions.getByType<LibraryExtension>()
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")


            with(pluginManager) {
                apply(libs.findPlugin("android-library").get().get().pluginId)
            }
            extensionAndroidCompose(extension)
        }
    }
}