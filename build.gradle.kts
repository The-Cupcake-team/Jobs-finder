
buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION_SAFE_ARGS}")
    }
}

plugins {
    id(Plugins.ANDROID_APPLICATION) version PluginsVersions.ANDROID_APPLICATION apply false
    id(Plugins.ANDROID_LIBRARY) version PluginsVersions.ANDROID_LIBRARY apply false
    kotlin(Plugins.KOTLIN_ANDROID) version PluginsVersions.KOTLIN_ANDROID apply false
    id(Plugins.KOTLIN_JVM) version PluginsVersions.KOTLIN_JVM apply false
    id(Plugins.HILT_LIBRARY) version PluginsVersions.HILT_LIBRARY apply false
    id(Plugins.SERIALIZATION) version PluginsVersions.SERIALIZATION apply false
}