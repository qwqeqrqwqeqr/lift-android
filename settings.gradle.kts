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
include(":core:model")
include(":core:database")
include(":core:network")
include(":core:datastore")
include(":core:firebase")
include(":core:test")
include(":core:common")
include(":core:designsystem")
include(":feature:home")
include(":feature:routine-info")
