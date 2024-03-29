plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.android.gradlePlugin)
    implementation(libs.firebase.performance.gradle)
    implementation(libs.firebase.crashlytics.gradle)

}

buildscript {
    dependencies{
    }
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
        register(property("ANDROID_LIBRARY_PLUGIN_ID").toString()) {
            id = property("ANDROID_LIBRARY_PLUGIN_ID").toString()
            implementationClass = property("ANDROID_LIBRARY_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("DATASTORE_PLUGIN_ID").toString()) {
            id = property("DATASTORE_PLUGIN_ID").toString()
            implementationClass = property("DATASTORE_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("FIREBASE_PLUGIN_ID").toString()) {
            id = property("FIREBASE_PLUGIN_ID").toString()
            implementationClass = property("FIREBASE_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("GOOGLE_PLUGIN_ID").toString()) {
            id = property("GOOGLE_PLUGIN_ID").toString()
            implementationClass = property("GOOGLE_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("HILT_PLUGIN_ID").toString()) {
            id = property("HILT_PLUGIN_ID").toString()
            implementationClass = property("HILT_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("IMAGE_PLUGIN_ID").toString()) {
            id = property("IMAGE_PLUGIN_ID").toString()
            implementationClass = property("IMAGE_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("KOTLIN_PLUGIN_ID").toString()) {
            id = property("KOTLIN_PLUGIN_ID").toString()
            implementationClass = property("KOTLIN_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("NETWORK_PLUGIN_ID").toString()) {
            id =property("NETWORK_PLUGIN_ID").toString()
            implementationClass = property("NETWORK_PLUGIN_IMPL_CLASS").toString()
        }
        register(property("OAUTH_PLUGIN_ID").toString()) {
            id =property("OAUTH_PLUGIN_ID").toString()
            implementationClass = property("OAUTH_PLUGIN_IMPL_CLASS").toString()
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
        register(property("WORK_PLUGIN_ID").toString()) {
            id =property("WORK_PLUGIN_ID").toString()
            implementationClass = property("WORK_PLUGIN_IMPL_CLASS").toString()
        }
    }
}
