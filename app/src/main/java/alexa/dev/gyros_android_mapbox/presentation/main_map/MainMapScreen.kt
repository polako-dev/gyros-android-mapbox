package alexa.dev.gyros_android_mapbox.presentation.main_map

import alexa.dev.gyros_android_mapbox.presentation.read_place.GyrosPlaceBottomSheet
import alexa.dev.gyros_android_mapbox.ui.theme.DarkBlue00
import alexa.dev.gyros_android_mapbox.ui.theme.LightBlue00
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.ViewAnnotation
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.viewannotation.geometry
import com.mapbox.maps.viewannotation.viewAnnotationOptions

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun MainMapScreen(
    viewModel: MainMapViewModel = hiltViewModel(),
    navController: NavController,
    onProfileClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    viewModel.checkPermissions()
    viewModel.getPlaces()
    val context = LocalContext.current

    val mockMarkers = state.gyrosPlacesUI

    val mapViewport = rememberMapViewportState {
        setCameraOptions {
            zoom(12.0)
            center(Point.fromLngLat(20.4628694, 44.8122387))
            bearing(0.0)
        }
    }

    val locationPermission =
        rememberPermissionState(permission = android.Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(Unit) {
        viewModel.uiAction.collect { action ->
            when (action) {
                is MainMapUIAction.ShowBottomSheet -> {}

                is MainMapUIAction.CenterMapOnUser -> {
                    mapViewport.transitionToFollowPuckState()
                }

                is MainMapUIAction.RequestLocationPermission -> {
                    if (!locationPermission.status.isGranted) {
                        locationPermission.launchPermissionRequest()
                    }
                }

                is MainMapUIAction.ShowError -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = LightBlue00,
                    titleContentColor = DarkBlue00,
                ),
                title = {
                    Text("Gyros")
                },
                actions = {
                    IconButton(
                        onClick = {
                            onProfileClick()
                        },
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        MapboxMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            mapViewportState = mapViewport,
        ) {
            MapEffect(Unit) { mapView ->
                mapView.location.updateSettings {
                    locationPuck = createDefault2DPuck(withBearing = true)
                    enabled = true
                    puckBearing = PuckBearing.COURSE
                    puckBearingEnabled = true
                }
                mapViewport.transitionToFollowPuckState()
            }

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
                    GyrosPlaceBottomSheet(
                        place = state.chosenGyros!!,
                        review = state.review,
                        onDismiss = { viewModel.dismissBs() })
                }
            }

        }

        LocateMeButton(
            onClick = {
                viewModel.centerOnUser()
            }
        )
    }
}
