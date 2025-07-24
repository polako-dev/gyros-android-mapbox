package alexa.dev.gyros_android_mapbox

//import alexa.dev.gyros_android_mapbox.auth.AuthRedirectHandler
import alexa.dev.gyros_android_mapbox.presentation.navigation.GyrosNavHost
import alexa.dev.gyros_android_mapbox.ui.theme.GyrosandroidmapboxTheme
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        handleAuthIntent(intent)

        enableEdgeToEdge()
        setContent {
            GyrosandroidmapboxTheme {
                GyrosNavHost()
            }
        }
    }

}