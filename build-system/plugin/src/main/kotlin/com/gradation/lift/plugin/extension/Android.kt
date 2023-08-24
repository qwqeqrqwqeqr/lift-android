/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gradation.lift.plugin.extension

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.extensionAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 34


        defaultConfig {
            minSdk = 23

            vectorDrawables {
                useSupportLibrary = true
            }
            buildFeatures {
                buildConfig = true
            }

            buildConfigField("String", "KAKAO_APP_KEY", getKey("KAKAO_APP_KEY", rootDir))
            buildConfigField(
                "String",
                "NAVER_OAUTH_CLIENT_ID",
                getKey("NAVER_OAUTH_CLIENT_ID", rootDir)
            )
            buildConfigField(
                "String",
                "NAVER_OAUTH_CLIENT_SECRET",
                getKey("NAVER_OAUTH_CLIENT_SECRET", rootDir)
            )
            buildConfigField(
                "String",
                "NAVER_OAUTH_CLIENT_NAME",
                getKey("NAVER_OAUTH_CLIENT_NAME", rootDir)
            )

            manifestPlaceholders["KAKAO_SCHEME"] = getKey("KAKAO_SCHEME", rootDir)
        }
        configureKotlin()











        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }





    }
}


private fun Project.configureKotlin() {
    // Use withType to workaround https://youtrack.jetbrains.com/issue/KT-55947
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            // Set JVM target to 11
            jvmTarget = JavaVersion.VERSION_17.toString()
            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                // Enable experimental coroutines APIs, including Flow
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
            )
        }
    }
}
fun getKey(propertyKey: String, rootDir: File): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}