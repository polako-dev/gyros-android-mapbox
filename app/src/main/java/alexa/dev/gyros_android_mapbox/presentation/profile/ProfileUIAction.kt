package alexa.dev.gyros_android_mapbox.presentation.profile

import android.net.Uri

sealed interface ProfileUIAction {
    object OnSuccess : ProfileUIAction
    data class LaunchGoogleAuth(val uri: Uri) : ProfileUIAction
    data class LaunchGithubAuth(val uri: Uri) : ProfileUIAction
}