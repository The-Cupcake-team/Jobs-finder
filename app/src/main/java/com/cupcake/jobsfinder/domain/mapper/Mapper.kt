package com.cupcake.jobsfinder.domain.mapper

interface Mapper<I, O> {
    fun mapTo(input: I): O
}