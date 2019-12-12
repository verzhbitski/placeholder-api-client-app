package org.verzhbitski.placeholderapiclient.view.ui.photolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.verzhbitski.placeholderapiclient.PlaceholderApiClientApp
import org.verzhbitski.placeholderapiclient.model.retrofit.Photo
import org.verzhbitski.placeholderapiclient.model.retrofit.PlaceholderApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PhotoListViewModel(private val albumId: Int) : ViewModel() {

    val photoList = MutableLiveData<List<Photo>>()
    val refreshing = MutableLiveData<Boolean>()
    val toastMessage = MutableLiveData<String>()

    @Inject lateinit var api: PlaceholderApi

    init {
        PlaceholderApiClientApp.getAppComponent().inject(this)
        fetch()
    }

    fun fetch() {
        refreshing.value = true

        api.getPhotosByAlbumId(albumId).enqueue(object : Callback<List<Photo>> {

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                refreshing.value = false
                toastMessage.value = t.localizedMessage
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                val body = response.body()

                if (body != null) photoList.value = body

                refreshing.value = false
            }
        })
    }
}
