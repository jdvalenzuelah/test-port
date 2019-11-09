package Terminal

import react.*
import react.dom.*
import typingAnimation.Typing

typealias RChildrenHandler = RDOMBuilder<kotlinx.html.Tag>.() -> Unit

interface CommandProps: RProps {
    var command: String
    var result: RChildrenHandler
    var status: String
    var onCommandCompleted: () -> Unit?
}

interface CommandState: RState {
    var commandCompleted: Boolean
}

class CommandUI(props: CommandProps): RComponent<CommandProps, CommandState>() {

    override fun CommandState.init(props: CommandProps) {
        commandCompleted = false
    }

    private fun completeCommand() {
        props.onCommandCompleted()
        setState { commandCompleted = true }
    }

    override fun RBuilder.render() {
        div("input") {
            div("status") { +props.status }
            Typing {
                attrs {
                    className = "command"
                    children = span {+" ${props.command}"}
                    onFinishedTyping = ::completeCommand
                    startDelay = 1500
                    if(!state.commandCompleted) { cursor = span("cursor") {+"_"} }
                }
            }
        }
        if(state.commandCompleted) {
            div("result") {
                props.result(this)
            }
        }
    }
}

fun RBuilder.commandUI(command: String, status: String, onCompletion: () -> Unit?, commandResult: RChildrenHandler) = child(CommandUI::class) {
    attrs.command = command
    attrs.result = commandResult
    attrs.onCommandCompleted = onCompletion
    attrs.status = status
}
