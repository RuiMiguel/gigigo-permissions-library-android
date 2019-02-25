package com.gigigo.ggglib.permissions.demoapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gigigo.ggglib.permissions.PermissionsActivity
import com.gigigo.ggglib.permissions.demoapp.R.string
import com.gigigo.ggglib.permissions.groups.PermissionGroupCamera.CAMERA
import com.gigigo.ggglib.permissions.groups.PermissionGroupLocation.ACCESS_FINE_LOCATION
import com.gigigo.ggglib.permissions.groups.PermissionGroupStorage.READ_EXTERNAL_STORAGE
import com.gigigo.ggglib.permissions.groups.PermissionGroupStorage.WRITE_EXTERNAL_STORAGE
import com.gigigo.ggglib.permissions.permission.Permission
import com.gigigo.ggglib.permissions.permission.PermissionCamera
import com.gigigo.ggglib.permissions.permission.PermissionLocation
import com.gigigo.ggglib.permissions.permission.PermissionStorage
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.fab_camera
import kotlinx.android.synthetic.main.activity_main.fab_clean
import kotlinx.android.synthetic.main.activity_main.fab_location
import kotlinx.android.synthetic.main.activity_main.fab_menu
import kotlinx.android.synthetic.main.activity_main.fab_storage_read
import kotlinx.android.synthetic.main.activity_main.fab_storage_write
import kotlinx.android.synthetic.main.content_main.message_text

class MainActivity : AppCompatActivity() {

    private var menuOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

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
            requestPermission(
                PermissionCamera(
                    CAMERA,
                    rationale = getString(R.string.permission_camera_needed)
                )
            )
        }
        fab_location.setOnClickListener {
            requestPermission(
                PermissionLocation(
                    ACCESS_FINE_LOCATION,
                    rationale = getString(R.string.permission_fine_location)
                )
            )
        }
        fab_storage_read.setOnClickListener {
            requestPermission(
                PermissionStorage(
                    READ_EXTERNAL_STORAGE,
                    rationale = getString(R.string.permission_read_sd)
                )
            )
        }
        fab_storage_write.setOnClickListener {
            requestPermission(
                PermissionStorage(
                    WRITE_EXTERNAL_STORAGE,
                    rationale = getString(R.string.permission_write_sd)
                )
            )
        }
    }

    private fun openMenu() {
        when (menuOpened) {
            true -> fab_menu.visibility = View.GONE
            false -> fab_menu.visibility = View.VISIBLE
        }
        menuOpened = !menuOpened
    }

    private fun requestPermission(permission: Permission) {
        PermissionsActivity.open(applicationContext,
            permission,
            onSuccess = {
                message_text.append(
                    getString(
                        R.string.permission_guarantied_message,
                        permission.getPermission()
                    )
                )
                Snackbar.make(
                    this@MainActivity.window.decorView.rootView,
                    getString(R.string.permission_guarantied),
                    Snackbar.LENGTH_LONG
                ).show()
            },
            onError = {
                message_text.append(
                    getString(
                        R.string.permission_denied_message,
                        permission.getPermission()
                    )
                )
                Snackbar.make(
                    this@MainActivity.window.decorView.rootView,
                    getString(string.permission_denied),
                    Snackbar.LENGTH_LONG
                )
                    .setAction(getString(string.retry)) { requestPermission(permission) }.show()
            })
    }
}
