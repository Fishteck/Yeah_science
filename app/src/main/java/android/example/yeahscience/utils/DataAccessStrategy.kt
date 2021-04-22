package android.example.yeahscience.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, A> performFetchOperation (
    databaseQuery:  () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit,
    deleteLocalData: (suspend () -> Unit)? = null
) : LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()

        if (responseStatus.status == Resource.Status.SUCCESS) {
            if (responseStatus.data != null) {
                deleteLocalData?.let { it() }
                saveCallResult(responseStatus.data)
            }
        } else if (responseStatus.status == Resource.Status.ERROR) {
            if (responseStatus.message != null) {
                emit(Resource.error(responseStatus.message))
                emitSource(source)
            }
        }
    }


fun <T> performRemoteOperation (
        networkCall : suspend () -> Resource<T>
    ) : LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {

            emit(Resource.loading())

            val response = networkCall.invoke()

            if (response.status == Resource.Status.SUCCESS) {

                if (response.data != null) {
                    val responser = MutableLiveData<Resource<T>>(Resource.success(response.data))
                    emitSource(responser)
                }

            } else if (response.status == Resource.Status.ERROR) {

                if (response.message != null) {
                    emit(Resource.error(response.message))
                }

            }
}

fun <T> performLocalOperation (
    databaseQuery :  () -> LiveData<T>
) : LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            val source = databaseQuery.invoke().map { Resource.success(it) }
            emitSource(source)
        } catch (ex : Exception) {
            emit( Resource.error(ex.localizedMessage?.toString(), null) )
        }

    }