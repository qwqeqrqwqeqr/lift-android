plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
    id("lift.android.test")
    id("lift.android.network")
    id("lift.android.datastore")
    id("lift.android.room")
    id("lift.android.work")
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
    implementation(project(":core:database"))
    implementation(project(":core:model"))
    implementation(project(":core:oauth"))
    androidTestImplementation(project(mapOf("path" to ":core:data")))
}