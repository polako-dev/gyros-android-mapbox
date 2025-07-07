package alexa.dev.gyros_android_mapbox.presentation.main_map

import alexa.dev.gyros_android_mapbox.domain.model.GyrosPlace
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.CircleAnnotation

@Composable
fun MainMapScreen() {
    val mockMarkers = listOf(
        GyrosPlace(1, "Gyros Place 1", 20.4573, 44.8176),
        GyrosPlace(2, "Gyros Place 2", 20.4600, 44.8190),
        GyrosPlace(3, "Gyros Place 3", 20.4550, 44.8150),
        GyrosPlace(4, "Gyros Place 4", 20.4620, 44.8180),
        GyrosPlace(5, "Gyros Place 5", 20.4590, 44.8160)
    )

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
                circleColor = Color(0xffee4e8b)
                circleStrokeWidth = 2.0
                circleStrokeColor = Color(0xffffffff)
            }
        }

    }
}