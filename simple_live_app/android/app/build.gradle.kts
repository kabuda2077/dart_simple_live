plugins {
    id("com.android.application")
    id("kotlin-android")
    // Flutter Gradle 插件必须在 Android 和 Kotlin 插件之后应用
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "com.xycz.simple_live"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = flutter.ndkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        applicationId = "com.xycz.simple_live"
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    // 移除了原来创建 "release" 签名的逻辑，因为它会去读取不存在或损坏的 key.properties

    buildTypes {
        release {
            // 关键修改：强制使用内置的 debug 签名配置
            // 这样即使是 Release 包，也会使用 Android SDK 自动生成的开发密钥签名
            signingConfig = signingConfigs.getByName("debug")
            
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

flutter {
    source = "../.."
}
