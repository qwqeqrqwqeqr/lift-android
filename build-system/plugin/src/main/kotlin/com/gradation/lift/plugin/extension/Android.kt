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
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.io.File

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.extensionAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 34

        defaultConfig {
            minSdk = 23

            vectorDrawables{
                useSupportLibrary = true
            }

            buildConfigField("String", "KAKAO_APP_KEY", getKey("KAKAO_APP_KEY",rootDir))
            buildConfigField("String", "NAVER_OAUTH_CLIENT_ID", getKey("NAVER_OAUTH_CLIENT_ID",rootDir))
            buildConfigField("String", "NAVER_OAUTH_CLIENT_SECRET", getKey("NAVER_OAUTH_CLIENT_SECRET",rootDir))
            buildConfigField("String", "NAVER_OAUTH_CLIENT_NAME", getKey("NAVER_OAUTH_CLIENT_NAME",rootDir))

            manifestPlaceholders["KAKAO_SCHEME"] = getKey("KAKAO_SCHEME",rootDir)
        }
        buildTypes{
        }




        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }




        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }

        packagingOptions {
            resources {
                excludes.add("META-INF/DEPENDENCIES")
                excludes.add("META-INF/LICENSE")
                excludes.add("META-INF/LICENSE.txt")
                excludes.add("META-INF/license.txt")
                excludes.add("META-INF/NOTICE")
                excludes.add("META-INF/NOTICE.txt")
                excludes.add("META-INF/notice.txt")
                excludes.add("META-INF/ASL2.0")
                excludes.add("META-INF/*.kotlin_module")
            }
        }

    }
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
fun getKey(propertyKey: String,rootDir: File): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}