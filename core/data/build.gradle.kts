plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
    id("lift.android.test")
    jacoco
}



jacoco{
    toolVersion="0.8.10"
}



android {
    namespace = "com.gradation.lift.data"
    defaultConfig {
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"
    }


}


dependencies{
    implementation(project(":core:test"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:datastore"))
    implementation(project(":core:model"))
}