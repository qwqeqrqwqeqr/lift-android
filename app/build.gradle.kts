plugins {
    id("lift.android.application")
    id("lift.android.compose.application")
    id("lift.android.hilt")
    id("lift.android.kotlin")
    id("lift.android.splash")
    id("lift.android.image")
    id("lift.android.oauth")
    id("lift.android.firebase")
}


android {
    namespace = "com.gradation.lift"

    defaultConfig {
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"
    }
}


dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:firebase"))
    implementation(project(":core:model"))
    implementation(project(":core:navigation"))
    implementation(project(":core:oauth"))
    implementation(project(":core:test"))
    implementation(project(":core:ui"))
    implementation(project(":core:work"))

    implementation(project(":feature:create-routine:navigation"))
    implementation(project(":feature:routine-detail:navigation"))
    implementation(project(":feature:register-detail:navigation"))
    implementation(project(":feature:login:navigation"))
    implementation(project(":feature:work:navigation"))
    implementation(project(":feature:badge:navigation"))
    implementation(project(":feature:notice:navigation"))
    implementation(project(":feature:update-routine:navigation"))
    implementation(project(":feature:my-info:navigation"))
    implementation(project(":feature:home:navigation"))
    implementation(project(":feature:daily-log:navigation"))
    implementation(project(":feature:analytics:navigation"))
    implementation(project(":feature:work-ready:navigation"))

}