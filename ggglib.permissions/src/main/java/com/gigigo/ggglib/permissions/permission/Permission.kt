package com.gigigo.ggglib.permissions.permission

import com.gigigo.ggglib.permissions.groups.PermissionGroup
import com.gigigo.ggglib.permissions.groups.PermissionGroupCalendar
import com.gigigo.ggglib.permissions.groups.PermissionGroupCamera
import com.gigigo.ggglib.permissions.groups.PermissionGroupContacts
import com.gigigo.ggglib.permissions.groups.PermissionGroupCustom
import com.gigigo.ggglib.permissions.groups.PermissionGroupLocation
import com.gigigo.ggglib.permissions.groups.PermissionGroupMicrophone
import com.gigigo.ggglib.permissions.groups.PermissionGroupPhone
import com.gigigo.ggglib.permissions.groups.PermissionGroupSensors
import com.gigigo.ggglib.permissions.groups.PermissionGroupSms
import com.gigigo.ggglib.permissions.groups.PermissionGroupStorage
import java.io.Serializable

//region See more info at https://developer.android.com/guide/topics/permissions/overview

//region PROTECTION_NORMAL: system automatically grants the app that permission at install time
/*
ACCESS_LOCATION_EXTRA_COMMANDS
ACCESS_NETWORK_STATE
ACCESS_NOTIFICATION_POLICY
ACCESS_WIFI_STATE
BLUETOOTH
BLUETOOTH_ADMIN
BROADCAST_STICKY
CHANGE_NETWORK_STATE
CHANGE_WIFI_MULTICAST_STATE
CHANGE_WIFI_STATE
DISABLE_KEYGUARD
EXPAND_STATUS_BAR
GET_PACKAGE_SIZE
INSTALL_SHORTCUT
INTERNET
KILL_BACKGROUND_PROCESSES
MANAGE_OWN_CALLS
MODIFY_AUDIO_SETTINGS
NFC
READ_SYNC_SETTINGS
READ_SYNC_STATS
RECEIVE_BOOT_COMPLETED
REORDER_TASKS
REQUEST_COMPANION_RUN_IN_BACKGROUND
REQUEST_COMPANION_USE_DATA_IN_BACKGROUND
REQUEST_DELETE_PACKAGES
REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
SET_ALARM
SET_WALLPAPER
SET_WALLPAPER_HINTS
TRANSMIT_IR
USE_FINGERPRINT
VIBRATE
WAKE_LOCK
WRITE_SYNC_SETTINGS
*/
//endregion

//region PROTECTION_SIGNATURE: The system grants these app permissions at install time, but only when the app that attempts to use a permission is signed by the same certificate as the app that defines the permission.
// Note: Some signature permissions aren't for use by third-party apps.
/*
BIND_ACCESSIBILITY_SERVICE
BIND_AUTOFILL_SERVICE
BIND_CARRIER_SERVICES
BIND_CHOOSER_TARGET_SERVICE
BIND_CONDITION_PROVIDER_SERVICE
BIND_DEVICE_ADMIN
BIND_DREAM_SERVICE
BIND_INCALL_SERVICE
BIND_INPUT_METHOD
BIND_MIDI_DEVICE_SERVICE
BIND_NFC_SERVICE
BIND_NOTIFICATION_LISTENER_SERVICE
BIND_PRINT_SERVICE
BIND_SCREENING_SERVICE
BIND_TELECOM_CONNECTION_SERVICE
BIND_TEXT_SERVICE
BIND_TV_INPUT
BIND_VISUAL_VOICEMAIL_SERVICE
BIND_VOICE_INTERACTION
BIND_VPN_SERVICE
BIND_VR_LISTENER_SERVICE
BIND_WALLPAPER
CLEAR_APP_CACHE
MANAGE_DOCUMENTS
READ_VOICEMAIL
REQUEST_INSTALL_PACKAGES
SYSTEM_ALERT_WINDOW
WRITE_SETTINGS
WRITE_VOICEMAIL
*/
//endregion

//region SPECIAL_PERMISSIONS: don't behave like normal and dangerous permissions
/*
SYSTEM_ALERT_WINDOW
WRITE_SETTINGS
*/
//endregion

//region DANGEROUS_PERMISSIONS: cover areas where the app wants data or resources that involve the user's private information, or could potentially affect the user's stored data or the operation of other apps
/*GROUP -> PERMISSION

CALENDAR
  READ_CALENDAR
  WRITE_CALENDAR

CAMERA
  CAMERA

CONTACTS
  READ_CONTACTS
  WRITE_CONTACTS
  GET_ACCOUNTS

LOCATION
  ACCESS_FINE_LOCATION
  ACCESS_COARSE_LOCATION

MICROPHONE
  RECORD_AUDIO

PHONE
  READ_PHONE_STATE
  READ_PHONE_NUMBERS
  CALL_PHONE
  ANSWER_PHONE_CALLS
  READ_CALL_LOG
  WRITE_CALL_LOG
  ADD_VOICEMAIL
  USE_SIP
  PROCESS_OUTGOING_CALLS

SENSORS
  BODY_SENSORS

SMS
  SEND_SMS
  RECEIVE_SMS
  READ_SMS
  RECEIVE_WAP_PUSH
  RECEIVE_MMS

STORAGE
  READ_EXTERNAL_STORAGE
  WRITE_EXTERNAL_STORAGE
*/
//endregion

//endregion

interface Permission : Serializable {
  val permissionGroup: PermissionGroup
  fun getPermission() = permissionGroup.permission
}

data class PermissionCustom(override val permissionGroup: PermissionGroupCustom) : Permission

data class PermissionCalendar(override val permissionGroup: PermissionGroupCalendar) : Permission

data class PermissionCamera(override val permissionGroup: PermissionGroupCamera) : Permission

data class PermissionContacts(override val permissionGroup: PermissionGroupContacts) : Permission

data class PermissionLocation(override val permissionGroup: PermissionGroupLocation) : Permission

data class PermissionMicrophone(override val permissionGroup: PermissionGroupMicrophone) : Permission

data class PermissionPhone(override val permissionGroup: PermissionGroupPhone) : Permission

data class PermissionSensors(override val permissionGroup: PermissionGroupSensors) : Permission

data class PermissionSms(override val permissionGroup: PermissionGroupSms) : Permission

data class PermissionStorage(override val permissionGroup: PermissionGroupStorage) : Permission