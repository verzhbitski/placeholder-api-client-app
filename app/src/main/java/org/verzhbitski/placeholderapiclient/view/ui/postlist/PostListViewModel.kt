package org.verzhbitski.placeholderapiclient.view.ui.postlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.verzhbitski.placeholderapiclient.PlaceholderApiClientApp
import org.verzhbitski.placeholderapiclient.model.retrofit.PlaceholderApi
import org.verzhbitski.placeholderapiclient.model.retrofit.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostListViewModel(private val userId: Int) : ViewModel() {

    val postList = MutableLiveData<List<Post>>()
    val refreshing = MutableLiveData<Boolean>()
    val toastMessage = MutableLiveData<String>()

    @Inject lateinit var api: PlaceholderApi

    init {
        PlaceholderApiClientApp.getAppComponent().inject(this)
        fetch()
    }

    fun fetch() {
        refreshing.value = true

        api.getPostsByUserId(userId).enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val body = response.body()

                if (body != null)
                    postList.value = body

                refreshing.value = false
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                refreshing.value = false
                toastMessage.value = t.localizedMessage
            }

        })

    }
}
