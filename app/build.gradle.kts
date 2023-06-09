plugins {
    id("lift.android.application")
    id("lift.android.common")
    id("lift.android.compose.application")
    id("lift.android.hilt")
    id("lift.android.kotlin")
    id("lift.android.splash")
}

android {
    namespace = "com.gradation.lift"
}
dependencies{
    implementation(project(":core:designsystem"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:navigation"))



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