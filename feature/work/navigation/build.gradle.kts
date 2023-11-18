plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
}

android {
    namespace = "com.gradation.lift.feature.work.navigation"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))

    implementation(project(":feature:work:change-order"))
    implementation(project(":feature:work:complete"))
    implementation(project(":feature:work:routine-selection"))
    implementation(project(":feature:work:work"))
}