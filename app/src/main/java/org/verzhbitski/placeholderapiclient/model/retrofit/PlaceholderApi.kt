package org.verzhbitski.placeholderapiclient.model.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceholderApi {

    @GET("/users")
    fun getUsers(): Call<List<User>>

    @GET("/posts")
    fun getPostsByUserId(@Query("userId") userId: Int): Call<List<Post>>

    @GET("/albums")
    fun getAlbumsByUserId(@Query("userId") userId: Int): Call<List<Album>>

    @GET("/photos")
    fun getPhotosByAlbumId(@Query("albumId") albumId: Int): Call<List<Photo>>
}