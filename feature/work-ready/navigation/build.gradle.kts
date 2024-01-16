plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
}

android {
    namespace = "com.gradation.lift.feature.workReady.navigation"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))

    implementation(project(":feature:work-ready:create-work-set"))
    implementation(project(":feature:work-ready:find-work-category"))
    implementation(project(":feature:work-ready:ready"))
    implementation(project(":feature:work-ready:routine-selection"))
}

