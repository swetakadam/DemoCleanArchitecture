package com.sweta.data.network.mappers

interface ApiMapper<E, D> {
    fun mapToDomain(apiEntity: E): D
}