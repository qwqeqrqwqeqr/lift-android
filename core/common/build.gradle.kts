plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
    id("lift.android.test")
    id("lift.android.work")
}

android {
    namespace = "com.gradation.lift.common"
    defaultConfig {
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"
    }
}

dependencies {
    implementation(project(":core:test"))
}
