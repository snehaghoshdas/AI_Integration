plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.12.0"
}

group = "com.aiSolution.hack"
version = "1.1.4"

repositories {
  mavenCentral()
}

intellij {
  version.set("2022.1.4")
  type.set("IC")
  plugins.set(listOf())
}

dependencies {
  implementation("com.fifesoft:rsyntaxtextarea:3.3.2")
  implementation("org.apache.poi:poi:3.9")
  implementation("org.apache.poi:poi-ooxml:5.2.0")
  implementation("org.apache.logging.log4j:log4j-api:2.16.0")
  implementation("org.apache.logging.log4j:log4j-core:2.16.0")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

tasks {
  patchPluginXml {
    sinceBuild.set("211")
    untilBuild.set("231.*")
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
}
