pluginManagement {
    includeBuild("build-system")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://devrepo.kakao.com/nexus/content/groups/public/") }
    }
}

rootProject.name = "lift"

include(":app")
include(":core:database")
include(":core:network")
include(":core:datastore")
include(":core:firebase")
include(":core:test")
include(":core:common")
include(":core:designsystem")
include(":core:data")
include(":core:domain")
include(":core:navigation")
include(":core:model")
include(":core:ui")
include(":core:work")


include(":feature:main:home")
include(":feature:main:history")
include(":feature:main:my-info")


include(":feature:create-routine:routine-set")
include(":feature:create-routine:find-work-category")
include(":feature:create-routine:profile")
include(":feature:create-routine:routine")


include(":feature:login:sign-in")
include(":feature:login:sign-up")
include(":feature:login:verification")
include(":feature:login:terms-of-use")
include(":feature:login:find-email")
include(":feature:login:find-password")
include(":feature:login:complete")


include(":feature:register-detail:name")
include(":feature:register-detail:gender")
include(":feature:register-detail:unit-of-weight")
include(":feature:register-detail:height-weight")
include(":feature:register-detail:profile-picture")




include(":feature:work:routine-selection")
include(":feature:work:change-order")
include(":feature:work:work")
include(":feature:work:complete")

