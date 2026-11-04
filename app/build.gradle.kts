plugins {
    id("com.android.application")
}

android {
    namespace = "com.bribot.sandfall"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.bribot.sandfall"
        minSdk = 24
        targetSdk = 36
        // Each CI build gets a higher version code, so it installs as an update.
        val buildNumber = System.getenv("GITHUB_RUN_NUMBER")?.toIntOrNull() ?: 0
        versionCode = 100 + buildNumber
        versionName = "2.0.$buildNumber"
    }

    // CI signs with the fixed Sandfall key (from repository secrets) so every
    // release installs over the previous one. Local builds fall back to the debug key.
    val keystorePath = System.getenv("SIGNING_KEYSTORE_PATH")
    signingConfigs {
        if (keystorePath != null) {
            create("sideload") {
                storeFile = file(keystorePath)
                storeType = "pkcs12"
                storePassword = System.getenv("SIGNING_PASSWORD")
                keyAlias = "sandfall"
                keyPassword = System.getenv("SIGNING_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.findByName("sideload") ?: signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
