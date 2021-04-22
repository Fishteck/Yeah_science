package android.example.yeahscience.data.spaceX.aboutCompany.models

import com.google.gson.annotations.SerializedName

data class SpaceXInfo (
    @SerializedName("headquarters")
     val spaceXHeadquarters: SpaceXHeadquarters,

     val links: SpaceXLinks,

    @SerializedName("name")
     val companyName: String,

     val founder: String,

    @SerializedName("founded")
     val foundationDate: Int,

    @SerializedName("employees")
     val numberOfEmployees: Int,

     val vehicles: Int,

    @SerializedName("launch_sites")
     val launchSites: Int,

    @SerializedName("test_sites")
     val testSites: Int,

    @SerializedName("ceo")
     val humanCeo: String,

    @SerializedName("cto")
     val humanCto: String,

    @SerializedName("coo")
     val humanCoo: String,

    @SerializedName("cto_propulsion")
     val humanCtoPropulsion: String,

     val valuation: Long,

    @SerializedName("summary")
     val description: String,

     val id: String
)