package com.gigigo.ggglib.permissions.demoapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.gigigo.ggglib.permissions.PermissionsActivity
import com.gigigo.ggglib.permissions.groups.PermissionGroupCamera.CAMERA
import com.gigigo.ggglib.permissions.permission.PermissionCamera
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.toolbar

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    fab.setOnClickListener { view ->
      requestPermission()
    }
  }

  private fun requestPermission() {
    PermissionsActivity.open(applicationContext,
        PermissionCamera(CAMERA),
        onSuccess = {
          Snackbar.make(this.window.decorView.rootView, "Permission guarantied!",
              Snackbar.LENGTH_LONG).show()
        },
        onError = {
          Snackbar.make(this.window.decorView.rootView, "Permission denied", Snackbar.LENGTH_LONG)
              .setAction("Retry", { requestPermission() }).show()
        })
  }
}
