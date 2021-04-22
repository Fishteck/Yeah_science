package android.example.yeahscience.utils.network

import android.example.yeahscience.utils.Resource
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {

    protected suspend fun <T> getResult( call : suspend () -> Response<T>) : Resource<T> {
        try {
            val response = call()
            if ( response.isSuccessful ) {
                val body = response.body()
                if ( body != null ) {
                    return Resource.success(
                        data = body
                    )
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (ex : Exception) {
            return error(ex.message ?: ex.toString())
        }
    }

    private fun <T> error( message : String) : Resource<T> {
        Timber.d(message)
        return Resource.error(message = "Network call has failed for a following reason: $message")
    }
}