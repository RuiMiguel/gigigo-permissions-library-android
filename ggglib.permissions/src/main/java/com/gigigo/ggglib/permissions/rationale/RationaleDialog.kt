package com.gigigo.ggglib.permissions.rationale

interface RationaleDialog {
    fun show(message: String, onAccept: () -> Unit)
}