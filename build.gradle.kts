import ProjectVersions.unethicaliteVersion

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    kotlin("jvm") version "1.6.21"
    kotlin("kapt") version "1.6.21"
}

project.extra["GithubUrl"] = "https://github.com/maguirekrist/public-plugins"
project.extra["GithubUserName"] = "maguirekrist"
project.extra["GithubRepoName"] = "public-plugins"

apply<JavaLibraryPlugin>()
apply<BootstrapPlugin>()
apply<CheckstylePlugin>()

allprojects {
    group = "com.maguirekrist"

    project.extra["PluginProvider"] = "maguirekrist"
    project.extra["ProjectSupportUrl"] = "https://google.com"
    project.extra["PluginLicense"] = "3-Clause BSD License"

    apply<JavaPlugin>()
    apply(plugin = "java-library")
    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
        mavenLocal()
    }


    dependencies {
        annotationProcessor(Libraries.lombok)
        annotationProcessor(Libraries.pf4j)

        compileOnly("net.unethicalite:http-api:$unethicaliteVersion+")
        compileOnly("net.unethicalite:runelite-api:$unethicaliteVersion+")
        compileOnly("net.unethicalite:runelite-client:$unethicaliteVersion+")
        compileOnly("net.unethicalite.rs:runescape-api:$unethicaliteVersion+")

        compileOnly(Libraries.okhttp3)
        compileOnly(Libraries.gson)
        compileOnly(Libraries.guice)
        compileOnly(Libraries.javax)
        compileOnly(Libraries.lombok)
        compileOnly(Libraries.pf4j)
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks {

        compileKotlin {
            kotlinOptions {
                jvmTarget = "11"
                freeCompilerArgs = listOf("-Xjvm-default=all-compatibility")
            }
            sourceCompatibility = "11"
        }

        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }

        withType<AbstractArchiveTask> {
            isPreserveFileTimestamps = false
            isReproducibleFileOrder = true
            dirMode = 493
            fileMode = 420
        }

    }
}
