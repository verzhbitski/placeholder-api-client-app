package org.verzhbitski.placeholderapiclient.view.ui.albumlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.verzhbitski.placeholderapiclient.PlaceholderApiClientApp
import org.verzhbitski.placeholderapiclient.model.retrofit.Album
import org.verzhbitski.placeholderapiclient.model.retrofit.PlaceholderApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AlbumListViewModel(private val userId: Int) : ViewModel() {

    val albumList = MutableLiveData<List<Album>>()
    val refreshing = MutableLiveData<Boolean>()
    val toastMessage = MutableLiveData<String>()

    @Inject lateinit var api: PlaceholderApi

    init {
        PlaceholderApiClientApp.getAppComponent().inject(this)
        fetch()
    }

    fun fetch() {
        refreshing.value = true

        api.getAlbumsByUserId(userId).enqueue(object : Callback<List<Album>> {
            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                refreshing.value = false
                toastMessage.value = t.localizedMessage
            }

            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                val body = response.body()

                if (body != null) albumList.value = body

                refreshing.value = false
            }

        })
    }
}
