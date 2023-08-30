import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information required by Maven Central
        pom {
            name.set("Cinematica SKD")
            description.set("This sdk will help to handle server request. It's created for client")
            url.set("https://github.com/CinematicaUA/sdk")

            licenses {
                license {
                    name.set("Apache-2.0")
                    url.set("https://opensource.org/licenses/Apache-2.0")
                }
            }
            developers {
                developer {
                    id.set("sliderzxc")
                    name.set("Vadym Hrynyk")
                    email.set("sliderzxc@gmail.com")
                }
            }
            issueManagement {
                system.set("Github")
                url.set("https://github.com/CinematicaUA/sdk/issues")
            }
            scm {
                connection.set("https://github.com/CinematicaUA/sdk.git")
                url.set("https://github.com/CinematicaUA/sdk")
            }
        }
    }
}

signing {
//    if (project.hasProperty("signing.gnupg.keyName")) {
//        useGpgCmd()
//    }
    useInMemoryPgpKeys(
        System.getenv("OSSRH_GPG_SECRET_KEY_ID"),
        System.getenv("OSSRH_GPG_SECRET_KEY"),
        System.getenv("OSSRH_GPG_SECRET_KEY_PASSWORD"),
    )
    sign(publishing.publications)
}

// TODO: remove after https://youtrack.jetbrains.com/issue/KT-46466 is fixed
project.tasks.withType(AbstractPublishToMaven::class.java).configureEach {
    dependsOn(project.tasks.withType(Sign::class.java))
}