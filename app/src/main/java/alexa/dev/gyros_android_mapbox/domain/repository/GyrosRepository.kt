package alexa.dev.gyros_android_mapbox.domain.repository

import alexa.dev.gyros_android_mapbox.domain.model.business.GyrosPlace
import alexa.dev.gyros_android_mapbox.domain.model.review.ReviewRemote
import alexa.dev.gyros_android_mapbox.domain.service.GyrosService
import javax.inject.Inject

class GyrosRepository @Inject constructor(
    private val api: GyrosService
) {
    suspend fun getAllGyros(): List<GyrosPlace> = api.getPlaces()

    suspend fun getReviewByBusinessId(id: Int): List<ReviewRemote> = api.getReviewByBusinessId(id)
}