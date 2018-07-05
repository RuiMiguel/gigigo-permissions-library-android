package com.gigigo.ggglib.permissions.demoapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.gigigo.ggglib.permissions.PermissionsActivity
import com.gigigo.ggglib.permissions.groups.PermissionGroupCamera.CAMERA
import com.gigigo.ggglib.permissions.groups.PermissionGroupCustom
import com.gigigo.ggglib.permissions.groups.PermissionGroupLocation.ACCESS_FINE_LOCATION
import com.gigigo.ggglib.permissions.groups.PermissionGroupStorage.READ_EXTERNAL_STORAGE
import com.gigigo.ggglib.permissions.groups.PermissionGroupStorage.WRITE_EXTERNAL_STORAGE
import com.gigigo.ggglib.permissions.permission.Permission
import com.gigigo.ggglib.permissions.permission.PermissionCamera
import com.gigigo.ggglib.permissions.permission.PermissionCustom
import com.gigigo.ggglib.permissions.permission.PermissionLocation
import com.gigigo.ggglib.permissions.permission.PermissionStorage
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.fab_camera
import kotlinx.android.synthetic.main.activity_main.fab_clean
import kotlinx.android.synthetic.main.activity_main.fab_location
import kotlinx.android.synthetic.main.activity_main.fab_menu
import kotlinx.android.synthetic.main.activity_main.fab_storage_read
import kotlinx.android.synthetic.main.activity_main.fab_storage_write
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_main.message_text

class MainActivity : AppCompatActivity() {

  private var menuOpened = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    initMenuClickListeners()
  }

  private fun initMenuClickListeners() {
    fab.setOnClickListener {
      openMenu()
    }

    fab_clean.setOnClickListener {
      message_text.text = ""
    }

    fab_camera.setOnClickListener {
      requestPermission(PermissionCamera(CAMERA, "camera its needed!"))
    }
    fab_location.setOnClickListener {
      requestPermission(PermissionLocation(ACCESS_FINE_LOCATION, "please let me find you!"))
    }
    fab_storage_read.setOnClickListener {
      requestPermission(PermissionStorage(READ_EXTERNAL_STORAGE, "I need to read from SD!"))
    }
    fab_storage_write.setOnClickListener {
      requestPermission(PermissionStorage(WRITE_EXTERNAL_STORAGE, "I need to write in SD!"))
    }

    // custom permission for new ones
    // requestPermission(PermissionCustom(PermissionGroupCustom("NEW PERMISSION")))
  }

  private fun openMenu() {
    when(menuOpened) {
      true -> fab_menu.visibility = View.GONE
      false -> fab_menu.visibility = View.VISIBLE
    }
    menuOpened = !menuOpened
  }

  private fun requestPermission(permission: Permission) {
    PermissionsActivity.open(applicationContext,
        permission,
        onSuccess = {
          message_text.append("${permission.getPermission()} guarantied! \n")
          Snackbar.make(this@MainActivity.window.decorView.rootView, "Permission guarantied!",
              Snackbar.LENGTH_LONG).show()
        },
        onError = {
          message_text.append("${permission.getPermission()} denied! \n")
          Snackbar.make(this@MainActivity.window.decorView.rootView, "Permission denied", Snackbar.LENGTH_LONG)
              .setAction("Retry", { requestPermission(permission) }).show()
        })
  }
}
