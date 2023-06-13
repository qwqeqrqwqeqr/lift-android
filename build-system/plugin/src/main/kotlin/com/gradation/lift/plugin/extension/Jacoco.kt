package com.gradation.lift.plugin.extension

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.scope.ProjectInfo.Companion.getBaseName
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.util.*

internal fun Project.extensionJacoco(
    jacocoPluginExtension: JacocoPluginExtension,
) {

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    val jacocoTestReport = tasks.create("jacocoTestReport")



    jacocoPluginExtension.apply {

        toolVersion = libs.findVersion("jacoco").get().toString()

        val testTaskName = "test${this}UnitTest"

        val reportTask = tasks.register(
            "jacoco${
                testTaskName.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
            }Report", JacocoReport::class) {

            dependsOn(testTaskName)

            reports {
                xml.required.set(false)
                csv.required.set(false)
                html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))

            }
        }

        jacocoTestReport.dependsOn(reportTask)
    }



}