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
