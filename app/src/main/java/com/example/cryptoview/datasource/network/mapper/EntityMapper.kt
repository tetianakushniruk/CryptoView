package com.example.cryptoview.datasource.network.mapper

interface EntityMapper<Entity, DomainModel> {

    fun mapToDomain(entity: Entity): DomainModel
}

