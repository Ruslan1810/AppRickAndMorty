package ru.ruslan.testpagin.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import ru.ruslan.testpagin.network.api.ApiInstance
import ru.ruslan.testpagin.network.api.ApiService
import kotlinx.coroutines.launch
import ru.ruslan.testpagin.screens.utils.CharacterPagingSource
import ru.ruslan.testpagin.screens.utils.RecyclerViewAdapter
import ru.ruslan.testpagin.network.api.model.CharacterData

class MainActivityViewModel : ViewModel() {

    private var apiService: ApiService =
        ApiInstance.getRetroInstance().create(ApiService::class.java)

    private fun getListData(): Flow<PagingData<CharacterData>> {
        return Pager(config = PagingConfig(pageSize = 1),
            pagingSourceFactory = { CharacterPagingSource(apiService) }).flow.cachedIn(
            viewModelScope
        )
    }

    fun loadData(recyclerViewAdapter: RecyclerViewAdapter) {

        viewModelScope.launch(Dispatchers.IO) {
            getListData().collect {
                recyclerViewAdapter.submitData(it)
            }
        }
    }
}


