package com.byjuassignmsaddam.utility

import androidx.annotation.StringRes
import java.io.PrintStream
import java.io.PrintWriter

class EvaluationFailedException : Exception {

    var messageResId: Int = 0
        private set
    var extraText: String? = null

    constructor(@StringRes messageResId: Int) {
        this.messageResId = messageResId
    }

    constructor(message: String) : super(message)

    constructor(@StringRes messageResId: Int, extraText: String) {
        this.messageResId = messageResId
        this.extraText = extraText
    }

    override fun printStackTrace(s: PrintStream) {
        s.println(messageResId)
        s.println(extraText)
        super.printStackTrace(s)
    }

    override fun printStackTrace(s: PrintWriter) {
        s.println(messageResId)
        s.println(extraText)
        super.printStackTrace(s)
    }
}