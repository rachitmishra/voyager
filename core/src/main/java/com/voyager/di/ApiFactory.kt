package com.voyager.di

interface ApiFactory {

    fun <T> create(service: Class<T>?): T
}
