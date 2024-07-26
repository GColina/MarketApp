plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.googleServices)
    kotlin("kapt")
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.gcolina.supermarkeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gcolina.supermarkeapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)

    // Glide
    implementation(libs.glide)


    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // lifecycle
    implementation(libs.androidx.activity.ktx)
}


/*
    // https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.retrofit2.converter.retrofit)
    implementation(libs.logging.interceptor)
    // Camera X
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.extensions)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)



androidx-navigation-fragment-ktx = { module = "androidx.navigation:navigation-fragment-ktx", version.ref = "navigationFragmentKtx" }
androidx-navigation-ui-ktx = { module = "androidx.navigation:navigation-ui-ktx", version.ref = "navigationFragmentKtx" }


   androidx-camera-core = { group = "androidx.camera", name = "camera-core", version.ref = "camera" }
androidx-camera-camera2 = { group = "androidx.camera", name = "camera-camera2", version.ref = "camera" }
androidx-camera-lifecycle = { group = "androidx.camera", name = "camera-lifecycle", version.ref = "camera" }
androidx-camera-view = { group = "androidx.camera", name = "camera-view", version.ref = "camera" }
androidx-camera-extensions = { group = "androidx.camera", name = "camera-extensions", version.ref = "camera" }



retrofit2-converter-gson= {module = "com.squareup.retrofit2:converter-gson" , version.ref = "retrofit2"}
retrofit2-converter-retrofit= {module = "com.squareup.retrofit2:retrofit" , version.ref = "retrofit2"}
logging-interceptor = {module = "com.squareup.okhttp3:logging-interceptor", version.ref = "interceptor"}




















*/