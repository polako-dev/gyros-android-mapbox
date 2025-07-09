package alexa.dev.gyros_android_mapbox.domain.model

data class GyrosPlace(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Int,
    val reviews: List<Review>? = null,
    val address: String
)


fun GyrosPlace.toGyrosCoords(color: Long): GyrosCoordinates {
    return GyrosCoordinates(
        id = this.id,
        latitude = this.latitude,
        longitude = this.longitude,
        rating = this.rating,
        color = color,
    )
}

