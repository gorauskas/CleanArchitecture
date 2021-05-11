pluginManagement {
    repositories {
        gradlePluginPortal()
    }

    plugins {
        val kotlinVersion = "1.5.0"

        kotlin("multiplatform") version kotlinVersion
        kotlin("jvm") version kotlinVersion
    }
}

include(
    ":domain",
    ":use-case",
    ":adapter"
)

