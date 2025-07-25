package alexa.dev.gyros_android_mapbox.domain.model.business

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewPlace(
    @SerialName("Name") val name: String,

    @SerialName("Address") val address: String = "кто прочитал тот лох",

    @SerialName("Rating") val rating: Int = 0,

    @SerialName("Latitude") val latitude: Double,

    @SerialName("Longitude") val longitude: Double
)
