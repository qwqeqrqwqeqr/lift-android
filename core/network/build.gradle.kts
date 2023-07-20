import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.network")
    id("lift.android.hilt")
    id("lift.android.test")
}

android {
    namespace = "com.gradation.lift.network"
    defaultConfig {
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"


        buildConfigField("String", "LIFT_API_URL", getApiURL("LIFT_API_URL"))
        buildConfigField("String", "LIFT_S3_URL", getApiURL("LIFT_S3_URL"))



    }
}


fun getApiURL(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}

dependencies {
    testImplementation(project(":core:test"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:datastore"))
}
