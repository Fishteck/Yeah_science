package android.example.yeahscience.data.spaceX.aboutCompany.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class SpaceXHeadquarters(
    private val address: String,
    private val city: String,
    private val state: String
)