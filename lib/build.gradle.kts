plugins {
    `java-library`
    `maven-publish`
}

group = "com.github.comodinoh"
version = "1.0.0"

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }

}


repositories {
    mavenCentral()
}

dependencies {
    api(libs.commons.math3)
    implementation(libs.guava)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}
