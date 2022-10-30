package com.voyager.di

interface UseCase<T> {

    suspend operator fun invoke(): T
}


