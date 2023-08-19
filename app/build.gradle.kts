plugins {
    id("lift.android.application")
    id("lift.android.common")
    id("lift.android.compose.application")
    id("lift.android.hilt")
    id("lift.android.kotlin")
    id("lift.android.splash")
    id("lift.android.image")
    id("lift.android.jacoco")
    id("lift.android.oauth")
    id("lift.android.firebase")
    id("jacoco")
}





android {
    namespace = "com.gradation.lift"


    defaultConfig {
        versionCode = 1
        versionName = "0.0.1"
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"






    }
}


dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:navigation"))
    implementation(project(":core:oauth"))
    implementation(project(":core:work"))
    implementation(project(":core:ui"))



    implementation(project(":feature:home"))
    implementation(project(":feature:my-info:my-info"))


    implementation(project(":feature:create-routine:routine-set"))
    implementation(project(":feature:create-routine:find-work-category"))
    implementation(project(":feature:create-routine:routine"))
    implementation(project(":feature:create-routine:profile"))

    implementation(project(":feature:login:complete"))
    implementation(project(":feature:login:sign-in"))
    implementation(project(":feature:login:sign-up"))
    implementation(project(":feature:login:find-email-password"))


    implementation(project(":feature:register-detail:gender"))
    implementation(project(":feature:register-detail:height-weight"))
    implementation(project(":feature:register-detail:name"))
    implementation(project(":feature:register-detail:profile-picture"))


    implementation(project(":feature:work:routine-selection"))
    implementation(project(":feature:work:change-order"))
    implementation(project(":feature:work:work"))
    implementation(project(":feature:work:complete"))


    implementation(project(":feature:history:daily-log"))

}