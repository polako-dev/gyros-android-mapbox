package alexa.dev.gyros_android_mapbox.presentation.profile

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUIState())
    val uiState = _uiState.asStateFlow()

    private val _uiAction = MutableSharedFlow<ProfileUIAction>()
    val uiAction = _uiAction.asSharedFlow()

    fun onGoogleLoginClick() {
        val uri = "https://gyros-api.polako.cloud/api/auth/google".toUri()
        Log.d("OAuthCHECK", "getting URL: $uri")
        viewModelScope.launch {
            _uiAction.emit(ProfileUIAction.LaunchGoogleAuth(uri))
        }
    }

    fun onGithubLoginClick() {
        val uri = "https://gyros-api.polako.cloud/api/auth/github".toUri()
        Log.d("OAuthCHECK", "getting URL: $uri")
        viewModelScope.launch {
            _uiAction.emit(ProfileUIAction.LaunchGithubAuth(uri))
        }
    }
}
