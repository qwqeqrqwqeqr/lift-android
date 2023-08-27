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
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    testImplementation(project(":core:test"))
}