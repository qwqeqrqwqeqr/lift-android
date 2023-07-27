import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("lift.android.library")
    id("lift.android.compose.library")
    id("lift.android.hilt")
    id("lift.android.kotlin")
    id("lift.android.test")
}

android {
    namespace = "com.gradation.lift.test"
    defaultConfig{
        buildConfigField("String", "LIFT_API_URL", getApiURL("LIFT_API_URL"))
        buildConfigField("String", "LIFT_S3_URL", getApiURL("LIFT_S3_URL"))

    }
}

fun getApiURL(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}
dependencies {
    implementation(project(":core:model"))
}

