package android.example.yeahscience.ui.main

import android.example.yeahscience.data.nasa.repository.NASARepository
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NASAPhotoTodayViewModel
@Inject constructor(private val repo : NASARepository
) : ViewModel() {
    val photoToday = repo.getPhotoToday()
}