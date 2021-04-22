package android.example.yeahscience.data.iss.models

import com.google.gson.annotations.SerializedName


data class ISSCurrentLocationResponse(
    val timestamp: Int,
    val message: String,
    @SerializedName("iss_position")
    val issCurrentLocation: ISSCurrentLocation
)
