plugins {
    id("com.android.application")
}

android {
    namespace = "com.bribot.sandfall"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.bribot.sandfall"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    // The game itself lives in /web so it can also be played in a browser.
    sourceSets {
        getByName("main") {
            assets.srcDirs("../web")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            // Signed with the debug key so the APK can be sideloaded straight away.
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
