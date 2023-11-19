package com.tonyydl.shoppingmallapp.data

data class Page<T> (
    val size: Int = 0,
    val page: Int = 0,
    val total: Int = 0,
    val results: List<T> = emptyList()
)