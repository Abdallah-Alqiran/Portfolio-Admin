plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    //firebase
    id("com.google.gms.google-services")

    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")

    // Serialization
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.alqiran.portfoliomainadmin"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.alqiran.portfoliomainadmin"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // for Testing
    testImplementation(libs.truth)
    androidTestImplementation(libs.truth)

    // testing
    testImplementation(kotlin("test"))

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")

    // Mockito (Java mocking framework)
    testImplementation("org.mockito:mockito-core:5.3.1")

    // Kotlinx Coroutines testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")


    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")

    testImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")


    //coil
    implementation(libs.coil)
    implementation(libs.coil.compose)

    // lottie
    implementation(libs.lottie)
    implementation(libs.lottie.compose)

    // viewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose.android)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)


    // dataStore
    implementation(libs.androidx.datastore.preferences)

    // dependency injection
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)


    //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.database.ktx)



    // extra icons
    implementation(libs.androidx.material)
    implementation(libs.androidx.material.icons.extended)


    // Serialization and navigation
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.compose.navigation)


    // Graphs
    implementation(libs.androidx.foundation)
    implementation(libs.ui)
}