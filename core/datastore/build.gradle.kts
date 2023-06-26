plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.datastore")
    id("lift.android.hilt")
    id("lift.android.test")
}

android {
    namespace = "com.gradation.lift.datastore"
    defaultConfig {
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"
    }
}

dependencies {
    implementation(project(":core:test"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
}