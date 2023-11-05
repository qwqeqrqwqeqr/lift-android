plugins {
    id("lift.android.application")
    id("lift.android.common")
    id("lift.android.compose.application")
    id("lift.android.hilt")
    id("lift.android.kotlin")
    id("lift.android.splash")
    id("lift.android.image")
    id("lift.android.oauth")
    id("lift.android.firebase")
}


java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


android {
    namespace = "com.gradation.lift"

    defaultConfig {
        versionCode = 9
        versionName = "0.0.14"
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

    implementation(project(":feature:create-routine:routine-set"))
    implementation(project(":feature:create-routine:find-work-category"))
    implementation(project(":feature:create-routine:profile-picture"))
    implementation(project(":feature:create-routine:routine"))

    implementation(project(":feature:routine-detail:navigation"))

    implementation(project(":feature:login:sign-in"))
    implementation(project(":feature:login:sign-up"))
    implementation(project(":feature:login:find-email-password"))
    implementation(project(":feature:login:complete"))


    implementation(project(":feature:register-detail:name"))
    implementation(project(":feature:register-detail:gender"))
    implementation(project(":feature:register-detail:height-weight"))
    implementation(project(":feature:register-detail:profile-picture"))

    implementation(project(":feature:work:routine-selection"))
    implementation(project(":feature:work:change-order"))
    implementation(project(":feature:work:work"))
    implementation(project(":feature:work:complete"))

    implementation(project(":feature:badge:badge"))
    implementation(project(":feature:badge:setting"))
    implementation(project(":feature:badge:new-badge"))

    implementation(project(":feature:home"))

    implementation(project(":feature:notification:notification"))
    implementation(project(":feature:notification:notice"))
    implementation(project(":feature:notification:notice-detail"))
    implementation(project(":feature:notification:push"))
    implementation(project(":feature:notification:push-detail"))


    implementation(project(":feature:history:history"))
    implementation(project(":feature:history:analytics"))
    implementation(project(":feature:history:daily-log"))

    implementation(project(":feature:my-info:my-info"))
    implementation(project(":feature:my-info:update-profile"))
    implementation(project(":feature:my-info:update"))

    implementation(project(":feature:update-routine:routine-set"))
    implementation(project(":feature:update-routine:routine"))
    implementation(project(":feature:update-routine:profile-picture"))
    implementation(project(":feature:update-routine:find-work-category"))

}