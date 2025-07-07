package alexa.dev.gyros_android_mapbox.presentation.main_map

import alexa.dev.gyros_android_mapbox.domain.model.GyrosPlace

data class MainMapUIState (
    val gyrosPlaces: List<GyrosPlace>? = null
)