object DependencyProject {

    private const val CORE_VERSION = "1.7.0"
    private const val APP_COMPAT_VERSION = "1.6.1"
    private const val MATERIAL_VERSION = "1.9.0"
    private const val CONSTRAINT_LAYOUT_VERSION = "2.1.4"
    private const val COIL_VERSION = "2.4.0"
    private const val JUNIT_VERSION = "4.13.2"
    private const val JUNIT_KTS_VERSION = "1.1.5"
    private const val ESPRESSO_VERSION = "3.5.1"
    private const val FRAGMENT_KTS_VERSION = "1.5.7"
    private const val ACTIVITY_KTS_VERSION = "1.7.1"
    private const val HILT_VERSION = "2.44"
    private const val DAGGER_VERSION = "2.35.1"
    private const val RETROFIT_VERSION = "2.9.0"
    private const val LOGGING_VERSION = "5.0.0-alpha.11"
    private const val KOTLIN_COROUTINES_VERSION = "1.6.4"
    private const val LIFECYCLE_VERSION = "2.6.1"
    private const val KOTLIN_SERIALIZATION_VERSION = "1.5.0"
    private const val NAVIGATION_VERSION = "2.6.0"
    private const val LOTTIE_VERSION = "6.0.1"
    private const val swipeRefreshLayoutVersion = "1.1.0"
    private const val DATASTORE_VERSION = "1.0.0"
    private const val ROOM_VERSION = "2.5.1"
    private const val SPLASH_SCREEN_VERSION = "1.0.0"

    const val androidxCore = "androidx.core:core-ktx:$CORE_VERSION"
    const val compat = "androidx.appcompat:appcompat:$APP_COMPAT_VERSION"
    const val material = "com.google.android.material:material:$MATERIAL_VERSION"
    const val constraint = "androidx.constraintlayout:constraintlayout:$CONSTRAINT_LAYOUT_VERSION"
    const val swipRefersh= "androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefreshLayoutVersion"

    const val junit = "junit:junit:$JUNIT_VERSION"
    const val junitExtension = "androidx.test.ext:junit:$JUNIT_KTS_VERSION"
    const val espresso = "androidx.test.espresso:espresso-core:$ESPRESSO_VERSION"

    const val fragment = "androidx.fragment:fragment-ktx:$FRAGMENT_KTS_VERSION"
    const val activity = "androidx.activity:activity-ktx:$ACTIVITY_KTS_VERSION"

    const val hilt = "com.google.dagger:hilt-android:$HILT_VERSION"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:$HILT_VERSION"

    const val dagger = "com.google.dagger:dagger-android:$DAGGER_VERSION"

    const val retrofit = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION"

    const val logging = "com.squareup.okhttp3:logging-interceptor:$LOGGING_VERSION"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$KOTLIN_COROUTINES_VERSION"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_VERSION"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$LIFECYCLE_VERSION"

    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:$KOTLIN_SERIALIZATION_VERSION"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_VERSION"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:$NAVIGATION_VERSION"

    const val lottie = "com.airbnb.android:lottie:$LOTTIE_VERSION"

    const val coil = "io.coil-kt:coil:$COIL_VERSION"

    const val dataStore = "androidx.datastore:datastore-preferences:$DATASTORE_VERSION"
    const val roomRuntime = "androidx.room:room-runtime:$ROOM_VERSION"
    const val roomCompiler = "androidx.room:room-compiler:$ROOM_VERSION"
    const val room = "androidx.room:room-compiler:$ROOM_VERSION"

    const val splashScreen = "androidx.core:core-splashscreen:$SPLASH_SCREEN_VERSION-beta02"


}