plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
}

android {
    namespace = "com.gradation.lift.feature.updateRoutine.navigation"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))

    implementation(project(":feature:update-routine:find-work-category"))
    implementation(project(":feature:update-routine:profile-picture"))
    implementation(project(":feature:update-routine:create-work-set"))
    implementation(project(":feature:update-routine:routine-set"))
}