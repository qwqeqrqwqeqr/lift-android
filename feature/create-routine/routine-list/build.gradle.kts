plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
}

android {
    namespace = "com.gradation.lift.feature.create_routine.routine_list"

}



dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:test"))
}