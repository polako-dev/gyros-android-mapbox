package alexa.dev.gyros_android_mapbox.presentation.main_map

import alexa.dev.gyros_android_mapbox.domain.model.business.toGyrosCoords
import alexa.dev.gyros_android_mapbox.domain.model.review.RatingColor
import alexa.dev.gyros_android_mapbox.domain.model.review.toReviewUI
import alexa.dev.gyros_android_mapbox.domain.repository.GyrosRepository
import alexa.dev.gyros_android_mapbox.utils.execute
import android.util.Log
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

    private val _uiState = MutableStateFlow(MainMapUIState())
    val uiState = _uiState.asStateFlow()

    private val _uiAction = MutableSharedFlow<MainMapUIAction>()
    val uiAction = _uiAction.asSharedFlow()

    fun checkPermissions() {
        viewModelScope.execute(
            source = {
                _uiAction.emit(MainMapUIAction.RequestLocationPermission)
            }
        )
    }

    fun centerOnUser() {
        viewModelScope.execute(
            source = {
                _uiAction.emit(MainMapUIAction.CenterMapOnUser)
            }
        )
    }

    fun getPlaces() {
        viewModelScope.execute(
            source = {
                gyrosRepository.getAllGyros()
            },
            onSuccess = { places ->
                Log.d("GYROS_DEBUGs", places.toString())
                _uiState.update {
                    it.copy(
                        gyroses = places,
                        gyrosPlacesUI = places.map { it.toGyrosCoords(setColor(it.rating)) }
                    )
                }
            },
            onComplete = { data ->
                Log.d("GYROS_DEBUGc", data.toString())
            },
            onError = { error ->
                Log.d("GYROS_DEBUGe", error.toString())
            }
        )
    }

    fun getChosenGyros(gyrosId: Int) {
        viewModelScope.execute(
            source = {
                uiState.value.gyroses.find { it.id == gyrosId }
            },
            onSuccess = { place ->
                _uiState.update {
                    it.copy(
                        chosenGyros = place
                    )
                }
                getReviewForChosenGyros(uiState.value.chosenGyros?.id)
            },
            onError = {}
        )
    }

    fun getReviewForChosenGyros(gyrosId: Int?) {
        gyrosId?.let {
            viewModelScope.execute(
                source = {
                    gyrosRepository.getReviewByBusinessId(gyrosId)
                },
                onSuccess = { review ->
                    _uiState.update {
                        it.copy(
                            review = review.map { it.toReviewUI() },
                            isBsVisible = true
                        )
                    }
                },
                onError = { error ->
                    Log.d("DEBUG", error.toString())
                }
            )
        }
    }

    private fun setColor(rating: Int): Long {
        return RatingColor.getRating(rating).color
    }

    fun dismissBs() {
        _uiState.update {
            it.copy(
                isBsVisible = false
            )
        }
    }
}