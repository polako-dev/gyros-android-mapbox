package alexa.dev.gyros_android_mapbox.presentation.main_map

import alexa.dev.gyros_android_mapbox.domain.model.business.GyrosPlace

sealed interface MainMapUIAction {
    data class ShowBottomSheet(val gyros: GyrosPlace) : MainMapUIAction

}