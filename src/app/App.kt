package app

import react.*
import react.dom.div
import Terminal.*
import kotlin.js.JSON.parse

val projects = """
    <a href="https://google.com">kkk</a>
""".trimIndent()

class App : RComponent<RProps, RState>() {
    fun kk() {}
    override fun RBuilder.render() {
        console.log(parse<List<Map<String, Any>>>("[{\"title\":\"uvg\"},{\"title\":\"qq\"}]"))
        div("App-header") {
            shell(
                    "josuevalenzuela",
                    "my-site",
                    listOf(
                            Command("whoami", "whoami"),
                            Command("find ./projects -maxdepth 1 -name \"*description.*\" -print0 | xargs -0 cat", "projects"),
                            Command("tail -n 100 /var/log/\$USER.log | grep work", "work"),
                            Command("cd ~/studies && less -FX history.txt || echo \"No history found\"", "studies"),
                            Command("sudo shutdown -h now", "shutdown")
                    )
            )
        }
    }
}

fun RBuilder.app() = child(App::class) {}
