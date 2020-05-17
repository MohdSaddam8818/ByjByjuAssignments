package com.byjuassignmentbysaddam.networkConnection.Request

import com.google.gson.annotations.SerializedName

data class RequestData (

    @SerializedName("q")
    val country: String?,
    @SerializedName("from")
    val from: Int?,
    @SerializedName("sortBy")
    val sortBy: String?,
    @SerializedName("apiKey")
    val apiKey: String?
)