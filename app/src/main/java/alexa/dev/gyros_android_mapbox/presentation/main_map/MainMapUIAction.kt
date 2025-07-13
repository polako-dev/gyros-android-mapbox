package alexa.dev.gyros_android_mapbox.presentation.main_map

import alexa.dev.gyros_android_mapbox.domain.model.business.GyrosPlace

sealed interface MainMapUIAction {
    data class ShowBottomSheet(val gyros: GyrosPlace) : MainMapUIAction
    object CenterMapOnUser : MainMapUIAction
    object RequestLocationPermission : MainMapUIAction
    data class ShowError(val message: String) : MainMapUIAction
}