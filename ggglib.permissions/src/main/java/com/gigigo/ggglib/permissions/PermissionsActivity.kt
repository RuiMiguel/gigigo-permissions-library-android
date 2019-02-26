package com.gigigo.ggglib.permissions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.gigigo.ggglib.permissions.permission.Permission

open class PermissionsActivity : AppCompatActivity() {

    private val PERMISSIONS_REQUEST_CODE = 1
    private lateinit var permissionRequested: Permission

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        title = ""

        val extraData = intent.getSerializableExtra(PERMISSION_REQUESTED)
        extraData?.let {
            permissionRequested = extraData as Permission
        }

        permissionRequested?.permissionGroup?.let {
            if (ContextCompat.checkSelfPermission(
                    this@PermissionsActivity,
                    it.permission
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                finishWithPermissionsGranted()
            } else {
                requestPermission()
            }
        }
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                permissionRequested.getPermission()
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    permissionRequested.getPermission()
                )
            ) {
                val permissionException = PermissionException(
                    PermissionError.PERMISSION_RATIONALE_ERROR,
                    permissionRequested.rationale
                )
                showRequestRationale(permissionException)
            } else {
                doRequestPermission()
            }
        }
    }

    private fun showRequestRationale(permissionException: PermissionException) {
        val dialog = AlertDialog.Builder(this)
            .setCancelable(false)
            .setMessage(permissionException.error)
            .setPositiveButton(android.R.string.ok) { _, _ -> doRequestPermission() }
            .create()

        dialog.show()
    }

    private fun doRequestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(permissionRequested.getPermission()), PERMISSIONS_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {

        when (requestCode) {
            PERMISSIONS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    finishWithPermissionsGranted()
                } else {
                    finishWithoutPermissions(
                        PermissionException(
                            PermissionError.PERMISSION_ERROR,
                            "Permission denied, boo! Disable the functionality that depends on this permission."
                        )
                    )
                }
            }
        }
    }

    private fun finishWithPermissionsGranted() {
        finish()
        onSuccess()
    }

    private fun finishWithoutPermissions(exception: PermissionException) {
        finish()
        onError(exception)
    }

    companion object Navigator {
        private const val PERMISSION_REQUESTED = "PERMISSION_REQUESTED"
        var onSuccess: () -> Unit = {}
        var onError: (PermissionException) -> Unit = {}

        fun open(
            context: Context, permission: Permission,

            onSuccess: () -> Unit = {},
            onError: (PermissionException) -> Unit = {}
        ) {

            this.onSuccess = onSuccess
            this.onError = onError

            val intent = Intent(context, PermissionsActivity::class.java)
            intent.putExtra(PERMISSION_REQUESTED, permission)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}