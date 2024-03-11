package com.gradation.lift.plugin.extension

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.gradation.lift.plugin.config.AndroidBuildConfig.COMPILE_SDK_VERSION
import com.gradation.lift.plugin.config.AndroidBuildConfig.MIN_SDK_VERSION
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import java.io.File


internal fun Project.extensionAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = COMPILE_SDK_VERSION


        defaultConfig {
            minSdk = MIN_SDK_VERSION

            vectorDrawables {
                useSupportLibrary = true
            }
            buildFeatures {
                buildConfig = true
            }

            buildConfigField(
                "String",
                "KAKAO_APP_KEY",
                getKey("KAKAO_APP_KEY", rootDir)
            )
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
            buildConfigField(
                "String",
                "GOOGLE_OAUTH_CLIENT_ID",
                getKey("GOOGLE_OAUTH_CLIENT_ID", rootDir)
            )
            buildConfigField(
                "String",
                "LIFT_API_URL",
                getKey("LIFT_API_URL", rootDir)
            )
            buildConfigField(
                "String",
                "LIFT_S3_URL",
                getKey("LIFT_S3_URL", rootDir)
            )
            manifestPlaceholders["KAKAO_SCHEME"] = getKey("KAKAO_SCHEME", rootDir)

            configureKotlin()
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            packaging {
                resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            }

        }

        signingConfigs {

            create("release") {
                storeFile = project.properties["STORE_FILE_PATH"]?.let { file(it) }
                storePassword = getKey("STORE_PASSWORD", rootDir)
                keyAlias = getKey("KEY_ALIAS", rootDir)
                keyPassword = getKey("KEY_PASSWORD", rootDir)
            }
        }

        buildTypes {
            getByName("debug")
            getByName("release") {
                signingConfigs.getByName("release")
            }
        }
    }
}


fun getKey(propertyKey: String, rootDir: File): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}