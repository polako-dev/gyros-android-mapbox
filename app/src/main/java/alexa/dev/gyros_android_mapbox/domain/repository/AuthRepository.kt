package alexa.dev.gyros_android_mapbox.domain.repository

import alexa.dev.gyros_android_mapbox.domain.model.auth.ExchangeCodeRequest
import alexa.dev.gyros_android_mapbox.domain.service.AuthService
import alexa.dev.gyros_android_mapbox.utils.PkceProvider
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthService,
    private val pkceProvider: PkceProvider
) {

//    suspend fun exchangeCode(code: String): String {
//        val response = api.sendCredentials(
//            ExchangeCodeRequest(code = code, codeVerifier = pkceProvider.getCodeVerifier())
//        )
//        return response.token
//    }
}
