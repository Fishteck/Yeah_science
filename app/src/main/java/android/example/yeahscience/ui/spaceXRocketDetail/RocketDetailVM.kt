package android.example.yeahscience.ui.spaceXRocketDetail

import android.example.yeahscience.data.spaceX.repository.SpaceXRepository
import android.example.yeahscience.data.spaceX.rockets.models.SpaceXRocket
import android.example.yeahscience.utils.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RocketDetailVM @Inject constructor (private val repository: SpaceXRepository) : ViewModel() {
    private val _id = MutableLiveData<String>()

    private val _rocket = _id.switchMap { id -> repository.getRocket(id) }

    val rocket: LiveData<Resource<SpaceXRocket>> = _rocket

    fun start(id: String) { _id.value = id}
}