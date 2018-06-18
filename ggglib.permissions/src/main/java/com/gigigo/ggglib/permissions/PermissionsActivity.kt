package com.gigigo.ggglib.permissions

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity

class PermissionsActivity : AppCompatActivity() {

  private val PERMISSIONS_REQUEST_CODE = 1
  private val PERMISSION_REQUESTED = Manifest.permission.CAMERA

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_permission)
    title = ""

    if (ContextCompat.checkSelfPermission(this@PermissionsActivity, PERMISSION_REQUESTED) == PackageManager.PERMISSION_GRANTED) {
      finishWithPermissionsGranted()
    } else {
      requestPermission()
    }
  }

  private fun requestPermission() {
    if (ContextCompat.checkSelfPermission(this, PERMISSION_REQUESTED) != PackageManager.PERMISSION_GRANTED) {

      if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_REQUESTED)) {
        var permissionException = PermissionException(PermissionError.PERMISSION_RATIONALE_ERROR,
            "Should show request permission rationale")

        Snackbar.make(window.decorView.rootView, permissionException.error, Snackbar.LENGTH_LONG)
            .setAction("retry", { doRequestPermission() })
            .show()
      } else {
        doRequestPermission()
      }
    }
  }

  private fun doRequestPermission() {
    ActivityCompat.requestPermissions(this,
        arrayOf(PERMISSION_REQUESTED), PERMISSIONS_REQUEST_CODE)
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
      grantResults: IntArray) {

    when (requestCode) {
      PERMISSIONS_REQUEST_CODE -> {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          finishWithPermissionsGranted()
        } else {
          finishWithoutPermissions(
              PermissionException(PermissionError.PERMISSION_ERROR,
                  "Permission denied, boo! Disable the functionality that depends on this permission."))
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
    var onSuccess: () -> Unit = {}
    var onError: (PermissionException) -> Unit = {}

    fun open(context: Context, permission: String, onSuccess: () -> Unit = {},
        onError: (PermissionException) -> Unit = {}) {

      this.onSuccess = onSuccess
      this.onError = onError

      val intent = Intent(context, PermissionsActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
      context.startActivity(intent)
    }
  }
}