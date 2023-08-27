plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
    id("lift.android.compose.library")
}

android {
    namespace = "com.gradation.lift.navigation"
}

dependencies {
    testImplementation(project(":core:test"))
}