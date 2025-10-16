package com.example.cmorenomusicapp.models

import com.google.gson.annotations.SerializedName

data class Album(
    val title : String,
    val artist : String,
    val description : String,
    val image : String,
    @SerializedName(value = "id", alternate = ["_id"]) val id : String
)
