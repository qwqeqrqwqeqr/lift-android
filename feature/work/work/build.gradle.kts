plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
}

android {
    namespace = "com.gradation.lift.feature.work.work"

}



dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:test"))
}