package com.gini.skilltest.ui.main.view.home.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gini.skilltest.data.model.Cat
import com.gini.skilltest.data.repository.MainRepository

class CatPagingSource(
    val size: String,
    val mime_types: String,
    val order: String,
    val limit: String,
    val catRepo: MainRepository
) : PagingSource<Int, Cat>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPage = params.key ?: 1
            val response = catRepo.getCatImages(size, mime_types, order, limit, nextPage.toString())

            return LoadResult.Page(
                data = response!!,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        TODO("Not yet implemented")
    }
}