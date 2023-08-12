package com.gradation.lift.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

class JacocoPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            val classDirectoriesTree = fileTree(buildDir) {
                include(
                    "**/classes/**/main/**",
                    "**/intermediates/classes/debug/**",
                    "**/intermediates/javac/debug/*/classes/**",
                    "**/tmp/kotlin-classes/debug/**"
                )

                exclude(
                    mutableSetOf(
                        "**/R.class",
                        "**/R\$*.class",
                        "**/BuildConfig.*",
                        "**/Manifest*.*",
                        "**/*Test*.*",
                        "android/**/*.*",
                        "**/*\$Lambda$*.*",
                        "**/*\$inlined$*.*"
                    )
                )
            }
            val sourceDirectoriesTree = fileTree("$buildDir") {
                include(
                    "src/main/java/**",
                    "src/main/kotlin/**",
                    "src/debug/java/**",
                    "src/debug/kotlin/**"
                )
            }
            val executionDataTree = fileTree(project.buildDir) {
                include(
                    "outputs/code_coverage/**/*.ec",
                    "jacoco/jacocoTestReportDebug.exec",
                    "jacoco/testDebugUnitTest.exec",
                    "jacoco/test.exec"
                )
            }

            with(pluginManager) {
                apply(libs.findPlugin("jacoco").get().get().pluginId)
            }

            configure<JacocoPluginExtension> {
                toolVersion = libs.findVersion("jacoco").get().toString()
            }

            tasks.register<JacocoReport>("jacocoTestReport") {
                group = "coverage"
                reports {
                    html.required.set(true)
                    html.outputLocation.set(file("$buildDir/reports/jacoco/jacocoTestReport/html"))
                }
                classDirectories.setFrom(classDirectoriesTree)
                sourceDirectories.setFrom(sourceDirectoriesTree)
                executionData.setFrom(executionDataTree)
            }
        }
    }
}