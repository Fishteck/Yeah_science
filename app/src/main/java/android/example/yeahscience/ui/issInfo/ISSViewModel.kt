package android.example.yeahscience.ui.issInfo

import android.example.yeahscience.data.iss.models.ISSCurrentLocationResponse
import android.example.yeahscience.data.iss.repository.ISSCurrentLocationRepository
import android.example.yeahscience.data.iss.repository.ISSCurrentLocationRepositoryImpl
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ISSViewModel @Inject constructor(repo : ISSCurrentLocationRepository<ISSCurrentLocationResponse>): ViewModel() {
    val currentLocation = repo.getISSCurrentLocation()
}