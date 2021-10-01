pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(
    ":domain",
    ":use-cases",
    ":adapters:authentication",
    ":adapters:config",
    ":adapters:graphql",
    ":adapters:repositories",
    ":adapters:server"
)

enableFeaturePreview("VERSION_CATALOGS")
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("kotlin", "1.6.0-M1")
            version("shadowjar", "7.0.0")
            version("coroutines", "1.5.2")
            version("ktor", "1.6.4")
            version("logback", "1.3.0-alpha10")
            version("koin", "3.1.2")
            version("kgraphql", "0.17.14")
            version("exposed", "0.35.1")
            version("jwt", "3.18.2")
            version("bcrypt", "0.9.0")
            version("hikari", "5.0.0")
            version("mariadb", "3.0.2-rc")
            version("h2", "1.4.200")
            version("flyway", "8.0.0-beta3")
            version("mockk", "1.12.0")
            version("updateversions", "0.39.0")
        }
    }
}

