package com.eddiej.searchbeers.data.source.remote.model

interface IMapper<T> {
    fun toEntity(): T
}