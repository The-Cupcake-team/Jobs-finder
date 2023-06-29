plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT_LIBRARY)
    id(Plugins.NAVIGATION_SAFE_ARGS)

}

android {
    namespace = "com.cupcake.jobsfinder"

    compileSdk=ConfigurationData.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.cupcake.jobsfinder"
        minSdk=ConfigurationData.MIN_SDK_VERSION
        targetSdk=ConfigurationData.TARGET_SDK_VERSION
        versionCode = ConfigurationData.VERSION_CODE
        versionName = ConfigurationData.VERSION_NAME
        testInstrumentationRunner = ConfigurationData.TEST_INSTRUMENTATION_RUNNER
        buildConfigField("String", "BASE_URL", ConfigurationData.getBaseUrl())
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

    implementation(project(Modules.DATA_REPO))
    implementation(project(Modules.DATA_REMOTE))
    implementation(project(Modules.DATA_LOCAL))
    implementation(project(Modules.DOMAIN_USECASE))
    implementation(project(Modules.PRESENTATION_UI))

    implementation(DependencyProject.androidxCore)
    implementation(DependencyProject.compat)
    testImplementation(DependencyProject.junit)
    androidTestImplementation(DependencyProject.junitExtension)
    androidTestImplementation(DependencyProject.espresso)
    implementation(DependencyProject.fragment)
    implementation(DependencyProject.activity)

    implementation(DependencyProject.hilt)
    kapt(DependencyProject.hiltCompiler)

    implementation(DependencyProject.retrofit)
    implementation(DependencyProject.gsonConverter)

    implementation(DependencyProject.logging)

    implementation(DependencyProject.navigationFragment)
    implementation(DependencyProject.navigationUi)

    implementation(DependencyProject.roomRuntime)
    annotationProcessor(DependencyProject.roomCompiler)
    kapt(DependencyProject.room)

    implementation(DependencyProject.splashScreen)

    implementation(DependencyProject.dataStore)

}