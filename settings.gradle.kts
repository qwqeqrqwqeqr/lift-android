pluginManagement {
    includeBuild("build-system")
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://devrepo.kakao.com/nexus/content/groups/public/") }
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
include(":core:oauth")


include(":feature:home")


include(":feature:create-routine:routine-set")
include(":feature:create-routine:find-work-category")
include(":feature:create-routine:profile")
include(":feature:create-routine:routine")

include(":feature:login:sign-in")
include(":feature:login:sign-up")
include(":feature:login:find-email-password")
include(":feature:login:complete")


include(":feature:register-detail:name")
include(":feature:register-detail:gender")
include(":feature:register-detail:height-weight")
include(":feature:register-detail:profile-picture")

include(":feature:work:routine-selection")
include(":feature:work:change-order")
include(":feature:work:work")
include(":feature:work:complete")

include(":feature:badge:badge")
include(":feature:badge:setting")


include(":feature:notification:notice")
include(":feature:notification:push")
include(":feature:notification:notification")

include(":feature:history:history")
include(":feature:history:analytics")
include(":feature:history:daily-log")

include(":feature:my-info:my-info")
include(":feature:my-info:update-profile")
include(":feature:my-info:update")
