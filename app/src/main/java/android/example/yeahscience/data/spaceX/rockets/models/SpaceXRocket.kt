package android.example.yeahscience.data.spaceX.rockets.models

import com.google.gson.annotations.SerializedName

data class SpaceXRocket (

    @SerializedName("id")
    val id : String,

    @SerializedName("flickr_images")
    val images :  List<String>?,

    @SerializedName("name")
    val name : String,

    @SerializedName("type")
    val type : String?,

    @SerializedName("stages")
    val stagesCount : Int,

    @SerializedName("cost_per_launch")
    val costOfOneLaunch : Int,

    @SerializedName("first_flight")
    val dateOfFirstFlight : String?,

    @SerializedName("company")
    val company: String,

    @SerializedName("description")
    val description : String?


)