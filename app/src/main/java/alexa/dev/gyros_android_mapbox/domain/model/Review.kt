package alexa.dev.gyros_android_mapbox.domain.model

data class Review(
    val userId: Int,
    val comment: String,
    val rating: Int,
    val businessId: Int
)
