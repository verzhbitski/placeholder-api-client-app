package org.verzhbitski.placeholderapiclient

import android.app.Application
import org.verzhbitski.placeholderapiclient.di.component.AppComponent
import org.verzhbitski.placeholderapiclient.di.component.DaggerAppComponent
import org.verzhbitski.placeholderapiclient.di.module.RetrofitModule
import org.verzhbitski.placeholderapiclient.model.retrofit.PlaceholderApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaceholderApiClientApp : Application() {

    companion object {
        private lateinit var appComponent: AppComponent

        fun getAppComponent() = appComponent
    }

    override fun onCreate() {
        super.onCreate()

        val api = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlaceholderApi::class.java)

        appComponent = DaggerAppComponent
            .builder()
            .retrofitModule(RetrofitModule(api))
            .build()
    }
}