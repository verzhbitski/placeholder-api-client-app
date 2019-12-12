package org.verzhbitski.placeholderapiclient.model.retrofit

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoCoordinates
)