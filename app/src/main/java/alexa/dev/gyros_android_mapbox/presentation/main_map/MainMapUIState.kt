package alexa.dev.gyros_android_mapbox.presentation.main_map

import alexa.dev.gyros_android_mapbox.domain.model.GyrosCoordinates
import alexa.dev.gyros_android_mapbox.domain.model.GyrosPlace

data class MainMapUIState (
    val gyrosPlaces: List<GyrosCoordinates> = emptyList(),
    val chosenGyros: GyrosPlace? = null,
    val isBsVisible: Boolean = false,
)