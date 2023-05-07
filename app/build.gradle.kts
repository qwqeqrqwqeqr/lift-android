
plugins {
    id("lift.android.application")
    id("lift.android.common")
    id("lift.android.compose.application")
    id("lift.android.hilt")
    id("lift.android.kotlin")
    id("lift.android.splash")
    id("lift.android.test")
}

android {
    namespace = "com.gradation.lift"
}
dependencies{
    implementation(project(":core:designsystem"))



    implementation(project(":feature:home"))
    implementation(project(":feature:routine"))
    implementation(project(":feature:history"))
    implementation(project(":feature:my-info"))

}