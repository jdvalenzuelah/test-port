package Terminal

import react.*
import react.dom.*

data class Command(val command: String,
                   val resultKey: String)

interface ShellProps: RProps {
    var user: String
    var dir: String
    var commandList: List<Command>
}

interface ShellState: RState {
    var commandsExecuted: Int?
    var last: Boolean
}

class Shell(props: ShellProps): RComponent<ShellProps, ShellState>() {

    private val history: MutableList<Command> = mutableListOf(props.commandList[0])

    override fun ShellState.init(props: ShellProps) {
        commandsExecuted = 0
    }

    fun addToHistory() {
        val index = state.commandsExecuted ?: 0
        if (index == props.commandList?.size -1) { return }
        history.add(props.commandList[index + 1])
        setState{
            commandsExecuted = index + 1
        }
    }

    private fun RBuilder.getResults(key: String) {
        when(key) {
            "whoami" -> pre {+aboutMe}
            "projects" -> span {
                div("projects") {
                    div("project") {
                        h1("title"){ +"Reciclaje UVG" }
                        p("description") {
                            +"""
                                Aplicacion para fomentar el reciclaje en lugares publicos.
                                La aplicacion fue desarrollada utilizando Vue + quasar para la interfaz grafica 
                            """.trimIndent()
                        }
                    }
                }
            }
            "shutdown" -> pre {
               +"""Password:
                Shutdown NOW!
                
                *** FINAL System shutdown message from ${props.user}@JV ***
                System going down IMMEDIATELY
                
                
                
                System shutdown time has arrived
                """.trimIndent()
            }
            else -> span("error") { +"No results found"}
        }
    }
    override fun RBuilder.render() {
        div("terminal") {
            for(command in history) {
                div("shell-command") {
                    attrs { key = command.resultKey }
                    div {
                        commandUI(command.command, "${props.user}@JV:~/${props.dir}\$", ::addToHistory) {
                            getResults(command.resultKey)
                        }
                    }
                }
            }
        }
    }

}

fun RBuilder.shell(user: String, dir: String, commands: List<Command>) = child(Shell::class){
    attrs.user = user
    attrs.dir = dir
    attrs.commandList = commands
}

val aboutMe = """ 
 _____ ____________     _______    _    ________      ________   ______     _______      _______
   |  |     |_____|     |______     \  / |_____||     |_____| \  |____|     |______|     |_____|
 __|  |___________|_____|______      \/  |     ||_____|_____|  \_/____|_____|______|_____|     |
                                   web developer and sys admin
"""
