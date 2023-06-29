plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.SERIALIZATION)
}

android {
    namespace ="com.cupcake.remote"
    compileSdk=ConfigurationData.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk=ConfigurationData.MIN_SDK_VERSION

        testInstrumentationRunner = ConfigurationData.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = ConfigurationData.JAVA_VERSIONS_CODE
        targetCompatibility = ConfigurationData.JAVA_VERSIONS_CODE
    }

    kotlinOptions {
        jvmTarget = ConfigurationData.JAVA_VERSIONS_CODE.toString()
    }

}

dependencies {

    implementation(DependencyProject.androidxCore)
    implementation(DependencyProject.compat)


    testImplementation(DependencyProject.junit)
    androidTestImplementation(DependencyProject.junitExtension)
    androidTestImplementation(DependencyProject.espresso)


    implementation(DependencyProject.retrofit)
    implementation(DependencyProject.gsonConverter)

    implementation(DependencyProject.logging)

    implementation(DependencyProject.kotlinxSerialization)

    implementation(DependencyProject.hilt)
    kapt(DependencyProject.hiltCompiler)
    implementation(DependencyProject.dataStore)
}