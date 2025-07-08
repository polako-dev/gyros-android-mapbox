package alexa.dev.gyros_android_mapbox.presentation.main_map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.CircleAnnotation

@Composable
fun MainMapScreen(
    viewModel: MainMapViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    viewModel.getPlaces()

    val mockMarkers = state.gyrosPlaces
    MapboxMap(
        Modifier.fillMaxSize(),
        mapViewportState = rememberMapViewportState {
            setCameraOptions {
                zoom(12.0)
                center(Point.fromLngLat(20.4628694, 44.8122387))
                pitch(0.0)
                bearing(0.0)
            }
        }
    ) {
        for (place in mockMarkers) {
            CircleAnnotation(point = Point.fromLngLat(place.latitude, place.longitude)) {
                circleRadius = 8.0
                circleColor = Color(place.color)
                circleStrokeWidth = 2.0
                circleStrokeColor = Color(0xffffffff)
            }
        }

    }
}