package alexa.dev.gyros_android_mapbox.presentation.main_map

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainMapViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(MainMapUIState())
    val uiState = _uiState.asStateFlow()
}