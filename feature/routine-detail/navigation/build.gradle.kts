plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
    id("lift.android.image")
}

android {
    namespace = "com.gradation.lift.feature.routineDetail.navigation"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))

    implementation(project(":feature:routine-detail:routine"))
    implementation(project(":feature:routine-detail:routine-list"))

}
