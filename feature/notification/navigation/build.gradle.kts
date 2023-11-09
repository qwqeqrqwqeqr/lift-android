plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
}

android {
    namespace = "com.gradation.lift.feature.notification.navigation"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    implementation(project(":core:oauth"))

    implementation(project(":feature:notification:notice"))
    implementation(project(":feature:notification:notice-detail"))
    implementation(project(":feature:notification:notification"))
    implementation(project(":feature:notification:push"))
    implementation(project(":feature:notification:push-detail"))
}