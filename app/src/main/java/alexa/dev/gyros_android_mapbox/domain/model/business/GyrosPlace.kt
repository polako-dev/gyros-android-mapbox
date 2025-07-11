package alexa.dev.gyros_android_mapbox.domain.model.business

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GyrosPlace(
    @SerialName("ID")
    val id: Int,

    @SerialName("CreatedAt")
    val createdAt: String? = null,

    @SerialName("UpdatedAt")
    val updatedAt: String? = null,

    @SerialName("DeletedAt")
    val deletedAt: String? = null,

    @SerialName("Name")
    val name: String,

    @SerialName("Address")
    val address: String,

    @SerialName("Rating")
    val rating: Int,

    @SerialName("Latitude")
    val latitude: Double,

    @SerialName("Longitude")
    val longitude: Double
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

//fun GyrosPlace.toGyrosUI(): GyrosUI {
//    return GyrosUI(
//        id = this.id,
//        name = this.id,
//        latitude = this.id,
//        longitude = this.id,
//        rating = this.id,
//        reviews = this.re,
//        address = this.address
//    )
//}

