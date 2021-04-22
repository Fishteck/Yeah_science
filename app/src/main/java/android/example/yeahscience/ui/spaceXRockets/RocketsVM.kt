package android.example.yeahscience.ui.spaceXRockets

import android.example.yeahscience.data.spaceX.repository.SpaceXRepository
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RocketsVM @Inject constructor(
    private val repository: SpaceXRepository
) : ViewModel() {
    val rockets = repository.getRockets()
}