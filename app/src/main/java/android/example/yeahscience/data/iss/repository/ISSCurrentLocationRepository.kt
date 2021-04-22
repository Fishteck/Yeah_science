package android.example.yeahscience.data.iss.repository

import android.example.yeahscience.utils.Resource
import androidx.lifecycle.LiveData

interface  ISSCurrentLocationRepository <T>{
     fun getISSCurrentLocation(): LiveData<Resource<T>>
}