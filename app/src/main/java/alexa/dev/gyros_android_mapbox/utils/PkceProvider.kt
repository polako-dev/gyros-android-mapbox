package alexa.dev.gyros_android_mapbox.utils

import android.util.Base64
import java.security.MessageDigest
import java.security.SecureRandom

class PkceProvider {
    data class PkceCode(
        val codeVerifier: String,
        val codeChallenge: String
    )

    fun generate(): PkceCode {
        val codeVerifier = generateCodeVerifier()
        val codeChallenge = generateCodeChallenge(codeVerifier)
        return PkceCode(codeVerifier, codeChallenge)
    }

    private fun generateCodeVerifier(): String {
        val secureRandom = SecureRandom()
        val code = ByteArray(32)
        secureRandom.nextBytes(code)
        return Base64.encodeToString(code, Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING)
    }

    private fun generateCodeChallenge(verifier: String): String {
        val bytes = verifier.toByteArray(Charsets.US_ASCII)
        val messageDigest = MessageDigest.getInstance("SHA-256")
        messageDigest.update(bytes, 0, bytes.size)
        val digest = messageDigest.digest()
        return Base64.encodeToString(digest, Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING)
    }
}