package alexa.dev.gyros_android_mapbox.presentation.navigation

import alexa.dev.gyros_android_mapbox.presentation.main_map.MainMapScreen
import alexa.dev.gyros_android_mapbox.presentation.profile.ProfileScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun GyrosNavHost(
    startDestination: String = "map"
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("map") {
            MainMapScreen(
                onProfileClick = {
                    navController.navigate("profile")
                },
                navController = navController
            )
        }

        composable("profile") {
            ProfileScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
