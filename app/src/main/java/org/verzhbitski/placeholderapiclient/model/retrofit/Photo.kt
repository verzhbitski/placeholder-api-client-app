package org.verzhbitski.placeholderapiclient.model.retrofit

data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)