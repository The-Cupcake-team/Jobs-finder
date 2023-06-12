plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = ConfigurationData.JAVA_VERSIONS_CODE
    targetCompatibility = ConfigurationData.JAVA_VERSIONS_CODE
}
