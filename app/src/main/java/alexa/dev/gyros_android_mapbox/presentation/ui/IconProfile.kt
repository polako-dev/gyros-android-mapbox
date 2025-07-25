package alexa.dev.gyros_android_mapbox.presentation.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IconProfile() {
    Icon(
        imageVector = Icons.Default.AccountCircle,
        contentDescription = "Profile Icon",
        modifier = Modifier.size(96.dp),
        tint = MaterialTheme.colorScheme.primary
    )
}