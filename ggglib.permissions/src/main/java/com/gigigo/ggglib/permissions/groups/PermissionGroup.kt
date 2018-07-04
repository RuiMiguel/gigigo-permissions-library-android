package com.gigigo.ggglib.permissions.groups

import android.Manifest

interface PermissionGroup {
  val permission: String
}

enum class PermissionGroupCalendar(override val permission: String) : PermissionGroup {
  READ_CALENDAR(Manifest.permission.READ_CALENDAR),
  WRITE_CALENDAR(Manifest.permission.WRITE_CALENDAR);
}

enum class PermissionGroupCamera(override val permission: String) : PermissionGroup {
  CAMERA(Manifest.permission.CAMERA);
}

enum class PermissionGroupContacts(override val permission: String) : PermissionGroup {
  READ_CONTACTS(Manifest.permission.READ_CONTACTS),
  WRITE_CONTACTS(Manifest.permission.WRITE_CONTACTS),
  GET_ACCOUNTS(Manifest.permission.GET_ACCOUNTS);
}

enum class PermissionGroupLocation(override val permission: String) : PermissionGroup {
  ACCESS_FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION),
  ACCESS_COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION);
}


enum class PermissionGroupMicrophone(override val permission: String) : PermissionGroup {
  RECORD_AUDIO(Manifest.permission.RECORD_AUDIO);
}

enum class PermissionGroupPhone(override val permission: String) : PermissionGroup {
  READ_PHONE_STATE(Manifest.permission.READ_PHONE_STATE),
  CALL_PHONE(Manifest.permission.CALL_PHONE),
  READ_CALL_LOG(Manifest.permission.READ_CALL_LOG),
  WRITE_CALL_LOG(Manifest.permission.WRITE_CALL_LOG),
  ADD_VOICEMAIL(Manifest.permission.ADD_VOICEMAIL),
  USE_SIP(Manifest.permission.USE_SIP),
  PROCESS_OUTGOING_CALLS(Manifest.permission.PROCESS_OUTGOING_CALLS);
}

enum class PermissionGroupSensors(override val permission: String) : PermissionGroup {
  BODY_SENSORS(Manifest.permission.BODY_SENSORS);
}

enum class PermissionGroupSms(override val permission: String) : PermissionGroup {
  SEND_SMS(Manifest.permission.SEND_SMS),
  RECEIVE_SMS(Manifest.permission.RECEIVE_SMS),
  READ_SMS(Manifest.permission.READ_SMS),
  RECEIVE_WAP_PUSH(Manifest.permission.RECEIVE_WAP_PUSH),
  RECEIVE_MMS(Manifest.permission.RECEIVE_MMS);
}


enum class PermissionGroupStorage(override val permission: String) : PermissionGroup {
  READ_EXTERNAL_STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE),
  WRITE_EXTERNAL_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE);
}