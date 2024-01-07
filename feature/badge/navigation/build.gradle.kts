plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
}

android {
    namespace = "com.gradation.lift.feature.badge.navigation"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))

    implementation(project(":feature:badge:badge"))
    implementation(project(":feature:badge:setting"))
}