package alexa.dev.gyros_android_mapbox.domain.model.review

import alexa.dev.gyros_android_mapbox.domain.model.business.GyrosPlace
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewRemote(
    @SerialName("ID") val id: Int,

    @SerialName("CreatedAt") val createdAt: String,

    @SerialName("UpdatedAt") val updatedAt: String,

    @SerialName("DeletedAt") val deletedAt: String? = null,

    @SerialName("Text") val text: String,

    @SerialName("BusinessID") val businessId: Int,

    @SerialName("Business") val business: GyrosPlace,

    @SerialName("Rating") val rating: Int
)

fun ReviewRemote.toReviewUI() : Review {
    return Review(
        id = this.id,
        comment = this.text,
        rating = this.rating,
        businessId = this.businessId
    )
}