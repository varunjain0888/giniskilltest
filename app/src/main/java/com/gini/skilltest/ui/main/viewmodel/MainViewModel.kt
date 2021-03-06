package com.gini.skilltest.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gini.skilltest.data.model.Cat
import com.gini.skilltest.data.repository.MainRepository
import com.gini.skilltest.ui.main.view.home.pagination.CatPagingSource
import kotlinx.coroutines.flow.Flow


class MainViewModel(private val repository: MainRepository) : ViewModel() {
    private lateinit var items: Flow<PagingData<Cat>>
    fun getImages(): Flow<PagingData<Cat>> {
        items = Pager(PagingConfig(20)) {
            CatPagingSource("small", "png,jpg", "ASC", "20", repository)
        }.flow
        return items
    }

    /*
    *  ANOTHER APPROACH IS WE CAN USE LIVEDATA AS WELL
    */

    /* fun getItems() = liveData(Dispatchers.IO) {
         emit(Resource.loading(data = null))
         try {
             emit(Resource.success(data = repository.getItems()))
         } catch (exception: Exception) {
             emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
         }
     }*/
}