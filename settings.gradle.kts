pluginManagement {
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
rootProject.name = "Voyager"
include("app")
include("network")
include(":core")
include(":chess")
include(":network-image")
include(":movies")
include(":db")
include(":weather")
include(":stocks")
include(":utils")
include(":design")
