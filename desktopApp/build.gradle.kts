import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm {}
    sourceSets {
        @Suppress("UNUSED_VARIABLE") val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":shared"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Minesweeper"
            packageVersion = "1.0.0"
            macOS {
                bundleID = "my.bundle.id"
                signing {
                    identity.set(project.rootDir.resolve("identity.txt").readText().trim())
                    prefix
                    keychain.set(project.rootDir.resolve("keychain.txt").readText().trim())
                    sign.set(true)
                }
            }

        }
    }
}
