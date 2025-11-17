plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
}

dependencies {
    implementation(project(":shared"))
    implementation(compose.desktop.currentOs)
}

kotlin { jvmToolchain(17) }

compose.desktop {
    application { mainClass = "com.example.kmptodo.desktop.MainKt" }
}
