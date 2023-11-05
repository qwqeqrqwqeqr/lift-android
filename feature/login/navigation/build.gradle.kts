plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
}

android {
    namespace = "com.gradation.lift.feature.login.navigation"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    implementation(project(":core:oauth"))

    implementation(project(":feature:login:complete"))
    implementation(project(":feature:login:find-email-password"))
    implementation(project(":feature:login:sign-in"))
    implementation(project(":feature:login:sign-up"))
}