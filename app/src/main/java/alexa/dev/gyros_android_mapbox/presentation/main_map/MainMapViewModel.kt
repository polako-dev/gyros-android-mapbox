package alexa.dev.gyros_android_mapbox.presentation.main_map

import alexa.dev.gyros_android_mapbox.domain.model.GyrosPlace
import alexa.dev.gyros_android_mapbox.domain.model.RatingColor
import alexa.dev.gyros_android_mapbox.domain.model.toGyrosCoords
import alexa.dev.gyros_android_mapbox.domain.repository.GyrosRepository
import alexa.dev.gyros_android_mapbox.utils.execute
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainMapViewModel @Inject constructor(
    private val gyrosRepository: GyrosRepository
) : ViewModel() {
    val mockMarkers = listOf(
        GyrosPlace(1, "Gyros Place 1", 20.4573, 44.8176, 1, address = "Bulevar Kralia Aleksandra 21"),
        GyrosPlace(2, "Gyros Place 2", 20.4600, 44.8190, 2, address = "Bulevar Kralia Aleksandra 21"),
        GyrosPlace(3, "Gyros Place 3", 20.4550, 44.8150, 3, address = "Bulevar Kralia Aleksandra 21"),
        GyrosPlace(4, "Gyros Place 4", 20.4620, 44.8180, 4, address = "Bulevar Kralia Aleksandra 21"),
        GyrosPlace(5, "Gyros Place 5", 20.4590, 44.8160, 5, address = "Bulevar Kralia Aleksandra 21")
    )

    private val _uiState = MutableStateFlow(MainMapUIState())
    val uiState = _uiState.asStateFlow()

    private val _uiAction = MutableSharedFlow<MainMapUIAction>()
    val uiAction = _uiAction.asSharedFlow()

    fun getPlaces() {
        viewModelScope.execute(
            source = {
                mockMarkers.map { gyros -> gyros.toGyrosCoords(setColor(gyros.rating)) }
            },
            onSuccess = { places ->
                _uiState.update {
                    it.copy(
                        gyrosPlaces = places
                    )
                }

            },
            onError = {}
        )
    }

    private fun setColor(rating: Int): Long {
        return RatingColor.getRating(rating).color
    }

    fun getChosenGyros(gyrosId: Int) {
        viewModelScope.execute(
            source = {},
            onSuccess = { place ->
                _uiState.update {
                    it.copy(
                        chosenGyros = mockMarkers[1], //in the future we will get one current gyros from remote by id
                        isBsVisible = true
                    )
                }

            },
            onError = {}
        )
    }

    fun dismissBs() {
        _uiState.update {
            it.copy(
                isBsVisible = false
            )
        }
    }
}