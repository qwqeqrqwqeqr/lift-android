plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.test")
    id("lift.android.room")
    id("lift.android.hilt")
}

android {
    namespace = "com.gradation.lift.database"
    defaultConfig {
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}
dependencies {
    implementation(project(":core:test"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))


    testImplementation(project(":core:test"))
    testImplementation(project(":core:common"))
}
