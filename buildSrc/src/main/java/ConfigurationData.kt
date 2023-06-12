import org.gradle.api.JavaVersion
import java.io.File
import java.util.Properties

object ConfigurationData {
    const val COMPILE_SDK_VERSION = 33
    const val MIN_SDK_VERSION = 24
    const val TARGET_SDK_VERSION = 33
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    val JAVA_VERSIONS_CODE = JavaVersion.VERSION_17
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    fun getBaseUrl(): String {
        val keyFile = File("local.properties")
        val properties = Properties()
        properties.load(keyFile.inputStream())
        return properties.getProperty("BASE_URL", "")
    }
}
