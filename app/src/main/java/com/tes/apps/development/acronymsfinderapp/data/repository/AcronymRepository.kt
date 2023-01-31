package com.tes.apps.development.acronymsfinderapp.data.repository

import com.tes.apps.development.acronymsfinderapp.data.remote.AcronymDtoItem
import com.tes.apps.development.acronymsfinderapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface AcronymRepository {

    suspend fun getAcronymLists(
        query: String
    ): Flow<Resource<List<AcronymDtoItem>>>

}