package com.example.projetmobile4a.model

class RestCountriesResponse{
    private val count: Int? = null
    private val next: String? = null
    private val results: MutableList<Countries>? = null

    fun getCount(): Int? {
        return count
    }

    fun getNext(): String? {
        return next
    }

    fun getResults(): MutableList<Countries>? {
        return results
    }
}