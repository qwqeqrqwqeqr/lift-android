plugins {
    id("lift.android.library")
    id("lift.android.kotlin")
    id("lift.android.hilt")
    id("lift.android.compose.library")
}

android {
    namespace = "com.gradation.lift.navigation"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:test"))



    implementation(project(":feature:main:home"))
    implementation(project(":feature:main:routine"))
    implementation(project(":feature:main:history"))
    implementation(project(":feature:main:my-info"))



    implementation(project(":feature:create-routine:find-work-category"))
    implementation(project(":feature:create-routine:find-workpart"))
    implementation(project(":feature:create-routine:routine"))
    implementation(project(":feature:create-routine:routine-set"))
    implementation(project(":feature:create-routine:routine-detail"))
}