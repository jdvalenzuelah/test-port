@file:JsModule("react-typing-animation")
@file:JsNonModule

package typingAnimation

import react.*

@JsName("default")
external val Typing: RClass<TypingProps>

external interface TypingProps: RProps {
    var className: String
    var children: ReactElement
    var onFinishedTyping: () -> Unit
    var onBeforeType: () -> Unit
    var startDelay: Int
    var cursor: ReactElement
}
