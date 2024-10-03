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

        maven {
            url = uri("https://jitpack.io")
        }
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven {
            url = uri("https://sdk.smartlook.com/android/release")
        }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }

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

rootProject.name = "TestTaskFoodApp"
include(":app")
include(":data")
include(":domain")
