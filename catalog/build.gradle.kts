plugins {
    id("lift.android.application")
    id("lift.android.compose.application")
    id("lift.android.kotlin")
}


android {
    namespace = "com.gradation.lift.catalog"
}


dependencies {
    implementation(project(":core:designsystem"))
}