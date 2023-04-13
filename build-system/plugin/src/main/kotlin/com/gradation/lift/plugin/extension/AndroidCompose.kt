package com.gradation.lift.plugin.extension

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.extensionAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
        composeOptions{
            kotlinCompilerExtensionVersion = "1.4.3"
        }

    }
}
