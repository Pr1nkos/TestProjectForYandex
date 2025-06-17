plugins {
    id("java")
}

group = "ru.pr1nkos"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val lombokVersion = "1.18.38"
val mockitoVersion = "5.18.0"
val log4jVersion = "2.24.3"

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    implementation("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    // https://mvnrepository.com/artifact/org.mockito/mockito-core
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    // https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter
    testImplementation("org.mockito:mockito-junit-jupiter:$mockitoVersion")

    implementation("org.apache.logging.log4j:log4j-api:${log4jVersion}")
    implementation("org.apache.logging.log4j:log4j-core:${log4jVersion}")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:${log4jVersion}")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs = listOf("-javaagent:${configurations.testRuntimeClasspath.get().files.find { it.name.contains("mockito-core") }!!.absolutePath}")
}