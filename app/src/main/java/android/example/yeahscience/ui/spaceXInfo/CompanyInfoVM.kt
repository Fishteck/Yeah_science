package android.example.yeahscience.ui.spaceXInfo

import android.example.yeahscience.data.spaceX.repository.SpaceXRepository
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompanyInfoVM @Inject constructor(private val repository: SpaceXRepository) : ViewModel(){
    val info = repository.getCompanyInfo()
}