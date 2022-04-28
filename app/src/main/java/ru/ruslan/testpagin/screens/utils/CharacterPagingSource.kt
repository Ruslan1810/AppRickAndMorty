package ru.ruslan.testpagin.screens.utils

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.ruslan.testpagin.network.api.ApiService
import ru.ruslan.testpagin.network.api.model.CharacterData

class CharacterPagingSource(private val apiService: ApiService) :
    PagingSource<Int, CharacterData>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        return try {
            val nextPage: Int = params.key ?: 1
            val response = apiService.getDataFromAPI(nextPage)

            val data = response.body()?.results ?: emptyList()

            val characterDataList = mutableListOf<CharacterData>()
            characterDataList.addAll(data)
            for (i in characterDataList){
                Log.d("Test", i.name.toString())
            }

            LoadResult.Page(
                data = characterDataList,
                prevKey = if (nextPage == 1) null else -1,
                nextKey = nextPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}