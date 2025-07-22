package alexa.dev.gyros_android_mapbox.domain.model.auth

data class ExchangeCodeRequest(
    val code: String,
    val codeVerifier: String
)

