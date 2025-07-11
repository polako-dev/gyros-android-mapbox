package alexa.dev.gyros_android_mapbox.domain.model.review

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val id: Int,
    val comment: String,
    val rating: Int,
    val businessId: Int
)