package org.verzhbitski.placeholderapiclient.view.ui.userlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.verzhbitski.placeholderapiclient.PlaceholderApiClientApp
import org.verzhbitski.placeholderapiclient.model.retrofit.PlaceholderApi
import org.verzhbitski.placeholderapiclient.model.retrofit.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserListViewModel : ViewModel() {

    val userList = MutableLiveData<List<User>>()
    val refreshing = MutableLiveData<Boolean>()
    val toastMessage = MutableLiveData<String>()

    @Inject lateinit var api: PlaceholderApi

    init {
        PlaceholderApiClientApp.getAppComponent().inject(this)
        fetch()
    }

    fun fetch() {
        refreshing.value = true

        api.getUsers().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                refreshing.value = false
                toastMessage.value = t.localizedMessage
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val body = response.body()

                if (body != null) {
                    userList.value = body
                }

                refreshing.value = false
            }

        })
    }
}
