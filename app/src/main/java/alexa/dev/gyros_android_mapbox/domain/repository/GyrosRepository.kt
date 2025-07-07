package alexa.dev.gyros_android_mapbox.domain.repository

import alexa.dev.gyros_android_mapbox.domain.service.GyrosService
import javax.inject.Inject

class GyrosRepository @Inject constructor(
    private val api: GyrosService
) {
//    suspend fun fetch() = api.get()
}