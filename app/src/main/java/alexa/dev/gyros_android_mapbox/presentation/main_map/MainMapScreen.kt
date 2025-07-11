package alexa.dev.gyros_android_mapbox.presentation.main_map

import alexa.dev.gyros_android_mapbox.presentation.read_place.GyrosPlaceBottomSheet
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.ViewAnnotation
import com.mapbox.maps.viewannotation.geometry
import com.mapbox.maps.viewannotation.viewAnnotationOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMapScreen(
    viewModel: MainMapViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    viewModel.getPlaces()

    val mockMarkers = state.gyrosPlacesUI

    LaunchedEffect(Unit) {
        viewModel.uiAction.collect { action ->
            when (action) {
                is MainMapUIAction.ShowBottomSheet -> {}
            }
        }
    }

    val mapViewport = rememberMapViewportState {
        setCameraOptions {
            zoom(12.0)
            center(Point.fromLngLat(20.4628694, 44.8122387))
            bearing(0.0)
        }
    }
    MapboxMap(
        modifier = Modifier.fillMaxSize(),
        mapViewportState = mapViewport,
    ) {
        for (place in mockMarkers) {
            ViewAnnotation(
                options = viewAnnotationOptions {
                    geometry(Point.fromLngLat(place.longitude, place.latitude))
                }
            ) {
                CustomButtonGyros(
                    color = Color(place.color),
                    onClick = { viewModel.getChosenGyros(place.id) }
                )
            }
        }

        if (state.isBsVisible == true) {
            ModalBottomSheet(
                onDismissRequest = { viewModel.dismissBs() }
            ) {
                GyrosPlaceBottomSheet(place = state.chosenGyros!!, review = state.review, onDismiss = { viewModel.dismissBs() })
            }
        }

    }
}
