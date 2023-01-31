package com.tes.apps.development.acronymsfinderapp.model.entity

import com.google.gson.annotations.SerializedName
import com.tes.apps.development.acronymsfinderapp.data.remote.Lf

class AcronymModel(
    val lfs: List<Lf> = listOf(),
    val sf: String = ""
)

