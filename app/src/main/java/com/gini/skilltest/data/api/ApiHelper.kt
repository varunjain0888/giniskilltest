package com.gini.skilltest.data.api


class ApiHelper(private val apiService: ApiService) {

    suspend fun getCatImages(
        size: String,
        mime_types: String,
        order: String,
        limit: String,
        page: String
    ) = apiService.getCatImages(size, mime_types, order, limit, page)
}