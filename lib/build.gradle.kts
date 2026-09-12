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

tasks.named("build") {
    dependsOn("runTests")
}

tasks.register<JavaExec>("runTests") {
    group = "verifying"

    dependsOn("testClasses")

    classpath = sourceSets["test"].runtimeClasspath

    mainClass.set("ro.comodinoh.TestRunner")

    jvmArgs("-ea")
}

tasks.named<Test>("test") {
    failOnNoDiscoveredTests = false
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}
