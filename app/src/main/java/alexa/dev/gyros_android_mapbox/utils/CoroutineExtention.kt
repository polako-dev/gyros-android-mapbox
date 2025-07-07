package alexa.dev.gyros_android_mapbox.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * source - source of data
 * onSuccess - block completes if source is successful
 * onComplete - completes anyway
 * onError - troughs an error
 */
inline fun <reified T> CoroutineScope.execute(
    crossinline source: suspend () -> T,
    crossinline onSuccess: (T) -> Unit = {},
    crossinline onComplete: (Result<T>) -> Unit = {},
    crossinline onError: (Throwable) -> Unit = {}
) {
    launch(Dispatchers.IO) {
        runCatching { source() }
            .onSuccess { result ->
                withContext(Dispatchers.Main) {
                    onSuccess(result)
                    onComplete(Result.success(result))
                }
            }
            .onFailure { throwable ->
                withContext(Dispatchers.Main) {
                    onError(throwable)
                    onComplete(Result.failure(throwable))
                }
            }
    }
}