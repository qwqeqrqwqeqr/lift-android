plugins {
    id("lift.android.library")
    id("lift.android.compose.library")
    id("lift.android.kotlin")
    id("lift.android.test")
}

android {
    namespace = "com.gradation.lift.ui"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    defaultConfig {
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"
    }
}
dependencies {
    testImplementation(project(":core:test"))

}
