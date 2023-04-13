package com.gradation.lift.plugin.extension

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project


internal fun Project.extensionKotlin(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }

    }
}