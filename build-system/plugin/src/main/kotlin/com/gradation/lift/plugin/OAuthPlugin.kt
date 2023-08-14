package com.gradation.lift.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType


class OAuthPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")


            dependencies {
                add("implementation", libs.findLibrary("kakao-auth").get())
                add("implementation", libs.findLibrary("google-auth").get())
                add("implementation", libs.findLibrary("naver-auth").get())
            }


        }
    }
}

