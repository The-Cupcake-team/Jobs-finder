plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.PARCELIZE)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)

}

android {
    namespace="com.cupcake.viewmodels"
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
    implementation(project(Modules.DOMAIN_USECASE))
    implementation("com.google.android.material:material:1.9.0")

    testImplementation(DependencyProject.junit)
    testImplementation(DependencyProject.material)
    androidTestImplementation(DependencyProject.junitExtension)

    implementation(DependencyProject.coroutines)
    implementation(DependencyProject.lifecycleRuntime)
    implementation(DependencyProject.lifecycleViewModel)
    implementation(DependencyProject.lifecycleLiveData)

    implementation(DependencyProject.fragment)
    implementation(DependencyProject.activity)

    implementation(DependencyProject.hilt)
    kapt(DependencyProject.hiltCompiler)

}