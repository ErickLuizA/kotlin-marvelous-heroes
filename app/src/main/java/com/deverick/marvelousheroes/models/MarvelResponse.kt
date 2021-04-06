package com.deverick.marvelousheroes.models

data class MarvelResponse<T> (
    val code: String,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: Data<T>,
    val etag: String
)

data class Data<T> (
    val offset: String,
    val limit: String,
    val total: String,
    val count: String,
    val results: MutableList<T>
)