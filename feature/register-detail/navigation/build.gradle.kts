plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
}

android {
    namespace = "com.gradation.lift.feature.registerDetail.navigation"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))

    implementation(project(":feature:register-detail:gender"))
    implementation(project(":feature:register-detail:height-weight"))
    implementation(project(":feature:register-detail:name"))
    implementation(project(":feature:register-detail:profile-picture"))
}