package alexa.dev.gyros_android_mapbox.presentation.profile

import alexa.dev.gyros_android_mapbox.presentation.ui.IconProfile
import alexa.dev.gyros_android_mapbox.presentation.ui.ProfileButton
import alexa.dev.gyros_android_mapbox.presentation.ui.ProfileSpacer
import alexa.dev.gyros_android_mapbox.presentation.ui.TopProfileBar
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiAction.collect { action ->
            when (action) {
                ProfileUIAction.OnSuccess -> {}

                is ProfileUIAction.LaunchGithubAuth -> {
                    CustomTabsIntent.Builder().build()
                        .launchUrl(context, action.uri)
                    Log.d("OAuthCHECK", "getting URL: ${action.uri}")
                }

                is ProfileUIAction.LaunchGoogleAuth -> {
                    CustomTabsIntent.Builder().build()
                        .launchUrl(context, action.uri)
                    Log.d("OAuthCHECK", "getting URL: ${action.uri}")
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopProfileBar(onBackClick)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ProfileSpacer(32)
            IconProfile()
            ProfileSpacer(16)

            Text(
                text = "mockemail@example.com",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(Modifier.height(32.dp))

            ProfileButton(
                onClick = {
                    Log.d("OAuthCHECK", "CLICK BTN")
                    viewModel.onGoogleLoginClick()
                },
                text = "Sign in with Google"
            )

            Spacer(Modifier.height(16.dp))

            ProfileButton(
                onClick = {
                    Log.d("OAuthCHECK", "CLICK BTN")
                    viewModel.onGithubLoginClick()
                },
                text = "Sign in with GitHub"
            )
        }
    }
}
