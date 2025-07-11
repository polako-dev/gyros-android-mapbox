package alexa.dev.gyros_android_mapbox.domain.model.business

import alexa.dev.gyros_android_mapbox.domain.model.review.Review

data class GyrosUI(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Int,
    val reviews: List<Review>? = null,
    val address: String
)
