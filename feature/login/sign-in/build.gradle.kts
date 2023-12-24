
plugins {
    id("lift.android.compose.library")
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
    id("lift.android.test")
}

android {
    namespace = "com.gradation.lift.feature.login.signIn"
    defaultConfig {
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"
    }
    testOptions {
        unitTests{
            isIncludeAndroidResources=true
        }
    }
}


dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:test"))
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":core:oauth"))
}