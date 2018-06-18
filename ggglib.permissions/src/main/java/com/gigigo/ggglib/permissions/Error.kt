package com.gigigo.ggglib.permissions

data class PermissionError(val code: Int, val message: String) {

  fun isValid(): Boolean = code != INVALID_ERROR

  companion object {
    val INVALID_ERROR = -1
    val PERMISSION_ERROR = 0x9990
    val PERMISSION_RATIONALE_ERROR = 0x9991
    val FATAL_ERROR = 0x9991
  }
}

class PermissionException(val code: Int, val error: String) : Exception()
