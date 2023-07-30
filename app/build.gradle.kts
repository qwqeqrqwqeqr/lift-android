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
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:navigation"))
    implementation(project(":core:work"))
    implementation(project(":core:ui"))



    implementation(project(":feature:main:home"))
    implementation(project(":feature:main:history"))
    implementation(project(":feature:main:my-info"))


    implementation(project(":feature:create-routine"))
    implementation(project(":feature:create-routine:find-work-category"))
    implementation(project(":feature:create-routine:routine"))
    implementation(project(":feature:create-routine:profile"))

    implementation(project(":feature:login:verification"))
    implementation(project(":feature:login:terms-of-use"))
    implementation(project(":feature:login:complete"))
    implementation(project(":feature:login:sign-in"))
    implementation(project(":feature:login:sign-up"))
    implementation(project(":feature:login:find-email"))
    implementation(project(":feature:login:find-password"))


    implementation(project(":feature:register-detail:gender"))
    implementation(project(":feature:register-detail:height-weight"))
    implementation(project(":feature:register-detail:name"))
    implementation(project(":feature:register-detail:unit-of-weight"))
    implementation(project(":feature:register-detail:profile-picture"))


    implementation(project(":feature:ready-work:selection"))
    implementation(project(":feature:ready-work:change-order"))

    implementation(project(":feature:work:work"))

}