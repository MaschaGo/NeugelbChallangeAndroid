import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
}

android {
    namespace = "com.maschago.neugelbchallangeandroid.data"
    compileSdk = (rootProject.extra["compileSdk"] as Int)

    defaultConfig {
        minSdk = (rootProject.extra["minSdk"] as Int)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        // Read API key from local.properties
        val localPropertiesFile = project.rootProject.file("apikey.properties")
        val tmdbApiKey: String? = if (localPropertiesFile.exists()) {
            Properties().apply {
                load(localPropertiesFile.inputStream())
            }.getProperty("tmdb_api_key")
        } else {
            null
        }

        buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")

    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
        unitTests.all {
            testCoverage {
                version = "0.8.8"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":core"))

    implementation(libs.core.ktx)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.core)

    implementation(libs.bundles.coroutine)
    implementation(libs.bundles.retrofit)
    implementation(libs.gson.core)
    implementation(libs.okhttp.logging)
    implementation(libs.paging.runtime)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.paging.test)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.mockito.kotlin)

    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.test.ext)
    androidTestImplementation(libs.test.espresso)
}