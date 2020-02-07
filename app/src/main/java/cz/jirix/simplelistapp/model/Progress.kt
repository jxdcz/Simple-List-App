package cz.jirix.simplelistapp.model

class Progress(
    val state: Int
) {

    companion object {
        const val LOADING = 0
        const val SUCCESS = 1
        const val ERROR = 2
        const val IDLE = 3

        fun loading() = Progress(LOADING)
        fun success() = Progress(SUCCESS)
        fun error() = Progress(ERROR)
        fun idle() = Progress(IDLE)
    }
}
