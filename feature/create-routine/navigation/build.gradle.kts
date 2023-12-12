plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
}

android {
    namespace = "com.gradation.lift.feature.createRoutine.navigation"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))

    implementation(project(":feature:create-routine:find-work-category"))
    implementation(project(":feature:create-routine:profile-picture"))
    implementation(project(":feature:create-routine:routine"))
    implementation(project(":feature:create-routine:routine-set"))
}