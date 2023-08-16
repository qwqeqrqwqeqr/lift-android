import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {

    id("lift.android.application")
    id("lift.android.common")
    id("lift.android.compose.application")
    id("lift.android.hilt")
    id("lift.android.kotlin")
    id("lift.android.splash")
    id("lift.android.image")
    id("lift.android.jacoco")
    id("lift.android.oauth")
    id("lift.android.firebase")
    id("jacoco")

}


val KAKAO_APP_KEY = getKey("KAKAO_APP_KEY")
val NAVER_OAUTH_CLIENT_ID = getKey("NAVER_OAUTH_CLIENT_ID")
val NAVER_OAUTH_CLIENT_SECRET = getKey("NAVER_OAUTH_CLIENT_SECRET")
val NAVER_OAUTH_CLIENT_NAME = getKey("NAVER_OAUTH_CLIENT_NAME")

android {
    namespace = "com.gradation.lift"


    defaultConfig {
        versionCode = 1
        versionName = "0.0.1"
        testInstrumentationRunner = "com.gradation.lift.test.LiftTestRunner"
        manifestPlaceholders["KAKAO_APP_KEY"] = KAKAO_APP_KEY

        buildConfigField("String", "KAKAO_APP_KEY", KAKAO_APP_KEY)
        buildConfigField("String", "NAVER_OAUTH_CLIENT_ID", NAVER_OAUTH_CLIENT_ID)
        buildConfigField("String", "NAVER_OAUTH_CLIENT_SECRET", NAVER_OAUTH_CLIENT_SECRET)
        buildConfigField("String", "NAVER_OAUTH_CLIENT_NAME", NAVER_OAUTH_CLIENT_NAME)

    }
}
fun getKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}



dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:navigation"))
    implementation(project(":core:oauth"))
    implementation(project(":core:work"))
    implementation(project(":core:ui"))



    implementation(project(":feature:main:home"))
    implementation(project(":feature:main:history"))
    implementation(project(":feature:main:my-info"))


    implementation(project(":feature:create-routine:routine-set"))
    implementation(project(":feature:create-routine:find-work-category"))
    implementation(project(":feature:create-routine:routine"))
    implementation(project(":feature:create-routine:profile"))

    implementation(project(":feature:login:verification"))
    implementation(project(":feature:login:terms-of-use"))
    implementation(project(":feature:login:complete"))
    implementation(project(":feature:login:sign-in"))
    implementation(project(":feature:login:sign-up"))
    implementation(project(":feature:login:find-email-password"))


    implementation(project(":feature:register-detail:gender"))
    implementation(project(":feature:register-detail:height-weight"))
    implementation(project(":feature:register-detail:name"))
    implementation(project(":feature:register-detail:unit-of-weight"))
    implementation(project(":feature:register-detail:profile-picture"))


    implementation(project(":feature:work:routine-selection"))
    implementation(project(":feature:work:change-order"))
    implementation(project(":feature:work:work"))
    implementation(project(":feature:work:complete"))

}