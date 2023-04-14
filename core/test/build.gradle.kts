plugins {
    id("lift.android.library")
    id("lift.android.hilt")
    id("lift.android.test")
}

android {
    namespace = "com.gradation.lift.test"

    defaultConfig {
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"
    }
}

dependencies {


}