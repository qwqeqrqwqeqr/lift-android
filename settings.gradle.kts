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
include(":catalog")

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
include(":feature:create-routine:profile-picture")
include(":feature:create-routine:routine")
include(":feature:create-routine:common")

include(":feature:login:sign-in")
include(":feature:login:sign-in-default")
include(":feature:login:sign-up-create-email")
include(":feature:login:sign-up-create-password")
include(":feature:login:terms-of-use")
include(":feature:login:terms-of-use-detail")
include(":feature:login:verify-email")
include(":feature:login:reset-password")
include(":feature:login:common")
include(":feature:login:navigation")


include(":feature:register-detail:name")
include(":feature:register-detail:gender")
include(":feature:register-detail:height-weight")
include(":feature:register-detail:profile-picture")
include(":feature:register-detail:common")
include(":feature:register-detail:navigation")


include(":feature:work:common")
include(":feature:work:routine-selection")
include(":feature:work:work")
include(":feature:work:complete")

include(":feature:badge:badge")
include(":feature:badge:setting")
include(":feature:badge:new-badge")


include(":feature:notification:notification")
include(":feature:notification:notice")
include(":feature:notification:notice-detail")
include(":feature:notification:push")
include(":feature:notification:push-detail")


include(":feature:history:history")
include(":feature:history:analytics")
include(":feature:history:daily-log")

include(":feature:my-info:my-info")
include(":feature:my-info:update-profile")
include(":feature:my-info:update")

include(":feature:update-routine:routine-set")
include(":feature:update-routine:profile-picture")
include(":feature:update-routine:routine")
include(":feature:update-routine:find-work-category")
include(":feature:update-routine:common")

include(":feature:routine-detail:routine")
include(":feature:routine-detail:routine-list")


include(":feature:routine-detail:navigation")
include(":feature:badge:navigation")
include(":feature:create-routine:navigation")
include(":feature:history:navigation")
include(":feature:work:navigation")
include(":feature:notification:navigation")
include(":feature:update-routine:navigation")
include(":feature:my-info:navigation")
