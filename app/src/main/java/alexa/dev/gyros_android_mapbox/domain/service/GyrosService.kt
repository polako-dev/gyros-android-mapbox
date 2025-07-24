package alexa.dev.gyros_android_mapbox.domain.service

import alexa.dev.gyros_android_mapbox.domain.model.business.GyrosPlace
import alexa.dev.gyros_android_mapbox.domain.model.business.NewPlace
import alexa.dev.gyros_android_mapbox.domain.model.review.ReviewRemote
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface GyrosService {
    @GET("api/businesses")
    suspend fun getPlaces(): List<GyrosPlace>

    @POST("api/businesses")
    suspend fun addPlace(@Body newGyros: NewPlace): GyrosPlace

    @GET("api/reviews/business/{id}")
    suspend fun getReviewByBusinessId(@Path("id") id: Int): List<ReviewRemote>
}
