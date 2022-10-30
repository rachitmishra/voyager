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
include(":core")
include(":movies")
include(":db")
// include(":weather")
//include(":stocks")
include(":design")
include(":location")
