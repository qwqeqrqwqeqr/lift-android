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

    implementation(project(":feature:create-routine:navigation"))
    implementation(project(":feature:routine-detail:navigation"))
    implementation(project(":feature:register-detail:navigation"))
    implementation(project(":feature:login:navigation"))
    implementation(project(":feature:work:navigation"))
    implementation(project(":feature:badge:navigation"))
    implementation(project(":feature:history:navigation"))
    implementation(project(":feature:notification:navigation"))
    implementation(project(":feature:update-routine:navigation"))
    implementation(project(":feature:my-info:navigation"))

    implementation(project(":feature:home"))
}