package com.gigigo.ggglib.permissions.rationale

import android.content.Context
import androidx.appcompat.app.AlertDialog

class DefaultRationaleDialog(private val context: Context): RationaleDialog {
    override fun show(message: String, onAccept: () -> Unit) {
        val dialog = AlertDialog.Builder(context)
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { _, _ -> onAccept() }
            .create()

        dialog.show()
    }
}