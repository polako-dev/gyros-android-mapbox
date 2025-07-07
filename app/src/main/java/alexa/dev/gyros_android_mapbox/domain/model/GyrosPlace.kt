package alexa.dev.gyros_android_mapbox.domain.model

data class GyrosPlace(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Int = 4,
    val reviews: List<String>? = null
)
