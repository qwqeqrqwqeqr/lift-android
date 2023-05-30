plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.datastore")
    id("lift.android.hilt")
}

android {
    namespace = "com.gradation.lift.datastore"
}

dependencies{
    implementation(project(":core:test"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
}