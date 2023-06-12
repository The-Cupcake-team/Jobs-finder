plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    kotlin(Plugins.KOTLIN_KAPT)
}

java {
    sourceCompatibility = ConfigurationData.JAVA_VERSIONS_CODE
    targetCompatibility = ConfigurationData.JAVA_VERSIONS_CODE
}

dependencies {
    api(project(Modules.DOMAIN_MODEL))
    implementation(DependencyProject.dagger)
}


