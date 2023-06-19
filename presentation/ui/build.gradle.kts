plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.NAVIGATION_SAFE_ARGS)
}

android {
    namespace ="com.cupcake.ui"
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
    buildFeatures {
        dataBinding = true
    }

}
dependencies {

    implementation(project(Modules.PRESENTATION_VIEWMODEL))

    implementation(DependencyProject.androidxCore)
    implementation(DependencyProject.compat)
    implementation(DependencyProject.material)
    implementation(DependencyProject.constraint)

    implementation(DependencyProject.navigationFragment)
    implementation(DependencyProject.navigationUi)
    implementation("com.google.android.material:material:1.9.0")

    testImplementation(DependencyProject.junit)
    androidTestImplementation(DependencyProject.junitExtension)
    androidTestImplementation(DependencyProject.espresso)

    implementation(DependencyProject.coroutines)
    implementation(DependencyProject.lifecycleRuntime)
    implementation(DependencyProject.lifecycleViewModel)
    implementation(DependencyProject.lifecycleLiveData)

    implementation(DependencyProject.hilt)
    kapt(DependencyProject.hiltCompiler)

    implementation(DependencyProject.fragment)
    implementation(DependencyProject.activity)

    implementation(DependencyProject.navigationFragment)
    implementation(DependencyProject.navigationUi)

    implementation(DependencyProject.coil)

    implementation(DependencyProject.lottie)

}