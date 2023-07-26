plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.test")
    id("lift.android.room")
    id("lift.android.hilt")


    
}

android {
    namespace = "com.gradation.lift.database"
}
dependencies {
    implementation(project(":core:test"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
}
