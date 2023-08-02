plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.work")
    id("lift.android.hilt")
    id("lift.android.test")
}

android {
    namespace = "com.gradation.lift.work"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}
dependencies {
    testImplementation(project(":core:test"))

}