import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(project(":shared"))

    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutinesSwing)

    implementation(libs.compose.uiToolingPreview)
}

compose.desktop {
    application {
        mainClass = "io.github.mrcoder20.ioclens.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)
            packageName = "io.github.mrcoder20.ioclens"
            packageVersion = "1.0.0"

            windows {
                iconFile.set(project.file("../docs/assets/logo/icon.ico"))
            }
            macOS {
                iconFile.set(project.file("../docs/assets/logo/icon.icns"))
            }
            linux {
                iconFile.set(project.file("../docs/assets/logo/icon.png"))
            }
        }
    }
}
