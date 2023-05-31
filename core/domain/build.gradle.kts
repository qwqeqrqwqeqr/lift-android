plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
}

android {
    namespace = "com.gradation.lift.domain"
    defaultConfig {
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"
    }
}

dependencies{
    testImplementation(project(":core:test"))
    implementation(project(":core:common"))
}