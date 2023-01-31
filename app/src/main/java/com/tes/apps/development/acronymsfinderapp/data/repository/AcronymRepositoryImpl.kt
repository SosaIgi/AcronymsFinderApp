package com.tes.apps.development.acronymsfinderapp.data.repository

import com.tes.apps.development.acronymsfinderapp.data.remote.AcronymApiService
import com.tes.apps.development.acronymsfinderapp.data.remote.AcronymDtoItem
import com.tes.apps.development.acronymsfinderapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AcronymRepositoryImpl @Inject constructor(
    private val api: AcronymApiService
): AcronymRepository {
    override suspend fun getAcronymLists(
        query: String
    ): Flow<Resource<List<AcronymDtoItem>>> {
        return flow {

            val remoteListings = try {
                //api.getAcronymsList()
                api.getAcronymsList(query)

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null // flow{null}
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null // flow{null}
            }
            remoteListings?.let { listings ->
                emit(Resource.Success(
                    data = listings
                          //  data = getShowListingFromDb("").map { it.toShowListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }
}