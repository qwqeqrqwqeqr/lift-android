plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
}

android {
    namespace = "com.gradation.lift.common"
}

dependencies {
    implementation(project(":core:test"))
}