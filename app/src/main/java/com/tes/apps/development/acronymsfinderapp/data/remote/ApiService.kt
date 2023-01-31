package com.tes.apps.development.acronymsfinderapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymApiService {

    @GET(END)
    suspend fun getAcronymsList(
        @Query("sf")sf:String

        //@Query("sf")sf:String = "Bad"
    ):AcronymDto

    companion object {
        const val BASE_URL = "http://www.nactem.ac.uk/"
        const val END = "software/acromine/dictionary.py"
    }

}
