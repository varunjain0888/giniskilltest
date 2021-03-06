package com.gini.skilltest.data.repository

import com.gini.skilltest.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getCatImages(
        size: String,
        mime_types: String,
        order: String,
        limit: String,
        page: String
    ) = apiHelper.getCatImages(size, mime_types, order, limit, page)

}