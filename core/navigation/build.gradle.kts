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
    implementation(project(":core:common"))
    implementation(project(":core:test"))



}