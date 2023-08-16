plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
    id("lift.android.test")
    id("lift.android.oauth")
}


android {
    namespace = "com.gradation.lift.oauth"

    defaultConfig{
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"
    }

}

dependencies {
    implementation(project(":core:test"))
    implementation(project(":core:common"))
    implementation(project(":core:datastore"))
    implementation(project(":core:model"))
}