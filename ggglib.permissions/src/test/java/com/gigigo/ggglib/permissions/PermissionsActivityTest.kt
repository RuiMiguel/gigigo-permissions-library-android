package com.gigigo.ggglib.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.gigigo.ggglib.permissions.groups.PermissionGroupCustom
import com.gigigo.ggglib.permissions.permission.Permission
import com.gigigo.ggglib.permissions.permission.PermissionCustom
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.internal.verification.api.VerificationData
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.verification.VerificationMode


@RunWith(MockitoJUnitRunner::class)
class PermissionsActivityTest {
    companion object {
        val ANY_PERMISSION = PermissionCustom(PermissionGroupCustom("permissionGroup"), "permissionRationale")
    }

    @Mock
    var context: Context? = null
    @Mock
    var permissionsActivity: PermissionsActivity? = null

    @Before
    fun setUp() {
        val mockApplicationContext = mock(Context::class.java)
        `when`(context?.applicationContext).thenReturn(mockApplicationContext)
    }


    @Test
    fun `on permission already granted then execute success`() {
        givenPermissionIsAlreadyGranted(ANY_PERMISSION)
        PermissionsActivity.open(
            context!!,
            ANY_PERMISSION,
            onSuccess = {},
            onError =  {}
        )
        thenPermissionIsGranted(ANY_PERMISSION)
    }

    private fun givenPermissionIsAlreadyDenied(permission: Permission) {
        givenPermissionIsChecked(permission, PackageManager.PERMISSION_DENIED)
    }

    private fun givenPermissionIsAlreadyGranted(permission: Permission) {
        givenPermissionIsChecked(permission, PackageManager.PERMISSION_GRANTED)
    }

    private fun givenPermissionIsChecked(permission: Permission, permissionState: Int) {
        `when`(
            ContextCompat.checkSelfPermission(
                context!!,
                permission.getPermission()
            )
        ).thenReturn(permissionState)
    }

    private fun givenShouldShowRationaleForPermission(permission: Permission) {
        `when`(
            ActivityCompat.shouldShowRequestPermissionRationale(
                any(PermissionsActivity::class.java),
                eq(permission.getPermission())
            )
        ).thenReturn(true)
    }

    private fun givenShouldNotShowRationaleForPermission(permission: Permission) {
        `when`(
            ActivityCompat.shouldShowRequestPermissionRationale(
                any(PermissionsActivity::class.java),
                eq(permission.getPermission())
            )
        ).thenReturn(false)
    }

    private fun thenPermissionIsGranted(permission: Permission) {
        verify(permissionsActivity)!!.finish()
    }
}