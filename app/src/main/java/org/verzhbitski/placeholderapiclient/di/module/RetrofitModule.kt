package org.verzhbitski.placeholderapiclient.di.module

import dagger.Module
import dagger.Provides
import org.verzhbitski.placeholderapiclient.model.retrofit.PlaceholderApi
import javax.inject.Singleton

@Module
class RetrofitModule(private val placeholderApi: PlaceholderApi) {

    @Provides
    @Singleton
    fun providePlaceholderApi(): PlaceholderApi {
        return placeholderApi
    }
}