plugins {
  java
}

val assignmentId: String by extra("H01")
val studentId: String by extra("_not_set_") // TU-ID
val firstName: String by extra("_not_set_")
val lastName: String by extra("_not_set_")

tasks {
  create<Jar>("prepareSubmission") {
    doFirst {
      if (studentId == "_not_set_" || firstName == "_not_set_" || lastName == "_not_set_") {
        throw GradleException("studentId or firstName or lastName not set!")
      }
    }
    // include source files in output jar
    from(sourceSets.main.get().allSource)
    // replace placeholders in resource directory
    // e.g. the submission-info.json file
    filesMatching("submission-info.json") {
      expand(project.properties)
    }
    // set the name of the output jar
    archiveFileName.set("$assignmentId-$lastName-$firstName-submission.jar")
  }
}
