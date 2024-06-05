version = "1.0.1"

project.extra["PluginName"] = "Fight Caves Helper"
project.extra["PluginDescription"] = "Some bullshit plugin."


dependencies {
    implementation ("org.apache.commons:commons-lang3:3.12.0")
}

tasks {
    jar {
        manifest {
            attributes(mapOf(
                "Plugin-Version" to project.version,
                "Plugin-Id" to nameToId(project.extra["PluginName"] as String),
                "Plugin-Provider" to project.extra["PluginProvider"],
                "Plugin-Description" to project.extra["PluginDescription"],
                "Plugin-License" to project.extra["PluginLicense"]
            ))
        }
    }
}