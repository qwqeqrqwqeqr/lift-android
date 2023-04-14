package com.gradation.lift.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import com.gradation.lift.plugin.extension.extensionAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            with(pluginManager) {
                apply(libs.findPlugin("android-library").get().get().pluginId)
                apply(libs.findPlugin("kotlin-android").get().get().pluginId)
            }
            extensions.configure<LibraryExtension> {
                extensionAndroid(this)
                defaultConfig.targetSdk = 33
            }
        }
    }
}