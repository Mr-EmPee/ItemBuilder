rootProject.name = "ItemBuilder"

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

  repositories {
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
  }
}