package alexa.dev.gyros_android_mapbox.presentation.main_map

import alexa.dev.gyros_android_mapbox.domain.model.business.GyrosCoordinates
import alexa.dev.gyros_android_mapbox.domain.model.business.GyrosPlace
import alexa.dev.gyros_android_mapbox.domain.model.review.Review

data class MainMapUIState (
    val gyrosPlacesUI: List<GyrosCoordinates> = emptyList(),
    val gyroses: List<GyrosPlace> = emptyList(),
    val review: List<Review> = emptyList(),
    val chosenGyros: GyrosPlace? = null,
    val isBsVisible: Boolean = false,
)