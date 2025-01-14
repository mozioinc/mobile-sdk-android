pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://raw.githubusercontent.com/mozioinc/mobile-sdk-android/main/maven/") {
            content {
                includeGroupByRegex("com.mozio.mobile.*")
            }
        }
        maven("https://jitpack.io") {
            content {
                includeModule("com.github.jeziellago", "compose-markdown")
                includeModule("com.github.jeziellago", "Markwon")
            }
        }
    }
}

rootProject.name = "mobile-sdk-android-sample-app"
include(":app")
 