plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.test")
}

android {
    namespace = "com.gradation.lift.model"
    defaultConfig {
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"
    }
}



dependencies {
    implementation(project(":core:test"))
}