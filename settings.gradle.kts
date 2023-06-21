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
    }
}

rootProject.name = "lift"

include(":app")
//include(":core:database")
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



include(":feature:main:home")
include(":feature:main:routine")
include(":feature:main:history")
include(":feature:main:my-info")


include(":feature:create-routine:routine-set")
include(":feature:create-routine:find-work-category")
include(":feature:create-routine:find-workpart")
include(":feature:create-routine:routine")
include(":feature:create-routine:routine-detail")

include(":feature:login:sign-in")
include(":feature:login:sign-up")
include(":feature:login:verification")
include(":feature:login:terms-of-use")
