

package com.sweta.democleanarchitecture.ui.permissions

sealed class PermissionAction {
  object PermissionGranted : PermissionAction()
  object PermissionDenied : PermissionAction()
}
