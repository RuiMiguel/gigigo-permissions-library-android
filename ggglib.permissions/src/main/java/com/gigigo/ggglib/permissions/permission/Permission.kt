package com.gigigo.ggglib.permissions.permission

import com.gigigo.ggglib.permissions.groups.PermissionGroup
import com.gigigo.ggglib.permissions.groups.PermissionGroupCalendar
import com.gigigo.ggglib.permissions.groups.PermissionGroupCamera
import com.gigigo.ggglib.permissions.groups.PermissionGroupContacts
import com.gigigo.ggglib.permissions.groups.PermissionGroupLocation
import com.gigigo.ggglib.permissions.groups.PermissionGroupMicrophone
import com.gigigo.ggglib.permissions.groups.PermissionGroupPhone
import com.gigigo.ggglib.permissions.groups.PermissionGroupSensors
import com.gigigo.ggglib.permissions.groups.PermissionGroupSms
import com.gigigo.ggglib.permissions.groups.PermissionGroupStorage
import java.io.Serializable

interface Permission : Serializable {
  val permissionGroup: PermissionGroup
  fun getPermission() = permissionGroup.permission
}

data class PermissionCalendar(override val permissionGroup: PermissionGroupCalendar) : Permission

data class PermissionCamera(override val permissionGroup: PermissionGroupCamera) : Permission

data class PermissionContacts(override val permissionGroup: PermissionGroupContacts) : Permission

data class PermissionLocation(override val permissionGroup: PermissionGroupLocation) : Permission

data class PermissionMicrophone(override val permissionGroup: PermissionGroupMicrophone) : Permission

data class PermissionPhone(override val permissionGroup: PermissionGroupPhone) : Permission

data class PermissionSensors(override val permissionGroup: PermissionGroupSensors) : Permission

data class PermissionSms(override val permissionGroup: PermissionGroupSms) : Permission

data class PermissionStorage(override val permissionGroup: PermissionGroupStorage) : Permission
