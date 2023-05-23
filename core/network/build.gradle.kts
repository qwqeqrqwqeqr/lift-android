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
    }
}

dependencies{
    implementation(project(":core:test"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
}