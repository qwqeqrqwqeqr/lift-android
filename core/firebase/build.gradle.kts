plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.test")
}

android {
    namespace = "com.gradation.lift.firebase"
}

dependencies {
    implementation(project(":core:test"))
}