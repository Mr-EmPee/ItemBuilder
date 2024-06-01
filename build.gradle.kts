plugins {
  id("java-library")
  id("io.freefair.lombok") version "8.4"
  id("com.vanniktech.maven.publish") version "0.28.0"
}

version = findProperty("tag") ?: "0.0.2-SNAPSHOT"

dependencies {
  compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))

mavenPublishing {
  coordinates("io.github.mr-empee", "item-builder", version.toString())

  pom {
    name.set("ItemBuilder")
    description.set("Allow easy creation of itemstacks")
    inceptionYear.set("2024")
    url.set("https://github.com/Mr-EmPee/ItemBuilder")
    licenses {
      license {
        name.set("The Apache License, Version 2.0")
        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
        distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
      }
    }

    developers {
      developer {
        id.set("mr-empee")
        name.set("Mr. EmPee")
        url.set("https://github.com/mr-empee/")
      }
    }

    scm {
      url.set("https://github.com/Mr-EmPee/ItemBuilder")
      connection.set("scm:git:git://github.com/Mr-EmPee/ItemBuilder.git")
      developerConnection.set("scm:git:ssh://git@github.com:Mr-EmPee/ItemBuilder.git")
    }
  }
}