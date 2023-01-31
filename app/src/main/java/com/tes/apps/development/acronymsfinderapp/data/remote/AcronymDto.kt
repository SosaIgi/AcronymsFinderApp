package com.tes.apps.development.acronymsfinderapp.data.remote


import com.google.gson.annotations.SerializedName
import com.tes.apps.development.acronymsfinderapp.model.entity.AcronymModel

class AcronymDto : ArrayList<AcronymDtoItem>()

data class AcronymDtoItem(
    @SerializedName("lfs")
    val lfs: List<Lf> = listOf(),
    @SerializedName("sf")
    val sf: String = ""
)

fun AcronymDtoItem.toAcronymModel() =AcronymModel(lfs=lfs, sf=sf)

data class Lf(
    @SerializedName("freq")
    val freq: Int = 0,
    @SerializedName("lf")
    val lf: String = "",
    @SerializedName("since")
    val since: Int = 0,
    @SerializedName("vars")
    val vars: List<Var> = listOf()
)

data class Var(
    @SerializedName("freq")
    val freq: Int = 0,
    @SerializedName("lf")
    val lf: String = "",
    @SerializedName("since")
    val since: Int = 0
)