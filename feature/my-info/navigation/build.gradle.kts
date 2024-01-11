plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
}

android {
    namespace = "com.gradation.lift.feature.myInfo.navigation"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))

    implementation(project(":feature:my-info:my-info"))
    implementation(project(":feature:my-info:update"))
    implementation(project(":feature:my-info:update-profile-picture"))
}