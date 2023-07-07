plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.firebase.performance.gradle)
    compileOnly(libs.firebase.crashlytics.gradle)
}


gradlePlugin {
    plugins {
        register(property("ANDROID_APPLICATION_PLUGIN_ID").toString()) {
            id = property("ANDROID_APPLICATION_PLUGIN_ID").toString()
            implementationClass = property("ANDROID_APPLICATION_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("ANDROID_COMPOSE_APPLICATION_PLUGIN_ID").toString()) {
            id = property("ANDROID_COMPOSE_APPLICATION_PLUGIN_ID").toString()
            implementationClass = property("ANDROID_COMPOSE_APPLICATION_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("ANDROID_COMPOSE_LIBRARY_PLUGIN_ID").toString()) {
            id = property("ANDROID_COMPOSE_LIBRARY_PLUGIN_ID").toString()
            implementationClass = property("ANDROID_COMPOSE_LIBRARY_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("ANDROID_COMMON_PLUGIN_ID").toString()) {
            id = property("ANDROID_COMMON_PLUGIN_ID").toString()
            implementationClass = property("ANDROID_COMMON_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("ANDROID_LIBRARY_PLUGIN_ID").toString()) {
            id = property("ANDROID_LIBRARY_PLUGIN_ID").toString()
            implementationClass = property("ANDROID_LIBRARY_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("DATASTORE_PLUGIN_ID").toString()) {
            id = property("DATASTORE_PLUGIN_ID").toString()
            implementationClass = property("DATASTORE_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("HILT_PLUGIN_ID").toString()) {
            id = property("HILT_PLUGIN_ID").toString()
            implementationClass = property("HILT_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("IMAGE_PLUGIN_ID").toString()) {
            id = property("IMAGE_PLUGIN_ID").toString()
            implementationClass = property("IMAGE_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("JACOCO_PLUGIN_ID").toString()) {
            id = property("JACOCO_PLUGIN_ID").toString()
            implementationClass = property("JACOCO_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("KOTLIN_PLUGIN_ID").toString()) {
            id = property("KOTLIN_PLUGIN_ID").toString()
            implementationClass = property("KOTLIN_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("NETWORK_PLUGIN_ID").toString()) {
            id =property("NETWORK_PLUGIN_ID").toString()
            implementationClass = property("NETWORK_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("ROOM_PLUGIN_ID").toString()) {
            id =property("ROOM_PLUGIN_ID").toString()
            implementationClass = property("ROOM_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("SPLASH_PLUGIN_ID").toString()) {
            id =property("SPLASH_PLUGIN_ID").toString()
            implementationClass = property("SPLASH_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("TEST_PLUGIN_ID").toString()) {
            id =property("TEST_PLUGIN_ID").toString()
            implementationClass = property("TEST_PLUGIN_IMPL_CLASS").toString()
        }
    }
}
