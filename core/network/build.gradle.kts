plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.network")
    id("lift.android.hilt")
}

android {
    namespace = "com.gradation.lift.network"
}

dependencies{
    implementation(project(":core:test"))
    implementation(project(":core:common"))
}