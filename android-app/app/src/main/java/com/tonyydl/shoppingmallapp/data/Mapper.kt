package com.tonyydl.shoppingmallapp.data

interface Mapper<T> {
    fun transform(): T
}