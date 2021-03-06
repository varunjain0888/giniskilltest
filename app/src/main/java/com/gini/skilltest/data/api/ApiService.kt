package com.gini.skilltest.data.api

import com.gini.skilltest.data.model.Cat
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/images/search")
    suspend fun getCatImages(
        @Query("size") size: String,
        @Query("mime_types") mime_types: String,
        @Query("order") order: String,
        @Query("limit") limit: String,
        @Query("page") page: String
    ): MutableList<Cat>?

}