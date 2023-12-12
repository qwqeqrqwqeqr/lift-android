plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
    id("lift.android.image")
}

android {
    namespace = "com.gradation.lift.feature.updateRoutine.profilePicture"

}



dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:test"))
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))

    implementation(project(":feature:update-routine:common"))

}
