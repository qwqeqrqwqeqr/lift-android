plugins {
    id("lift.android.library")
    id("lift.android.compose.library")
    id("lift.android.kotlin")
    id("lift.android.test")
    id("lift.android.image")
}

android {
    namespace = "com.gradation.lift.designsystem"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}
dependencies {
    testImplementation(project(":core:test"))

}
