plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
    id("lift.android.test")
    id("lift.android.oauth")
    id("lift.android.firebase")
    id("lift.android.google")
}


android {
    namespace = "com.gradation.lift.oauth"

    defaultConfig{
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"
    }

}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    testImplementation(project(":core:test"))
}