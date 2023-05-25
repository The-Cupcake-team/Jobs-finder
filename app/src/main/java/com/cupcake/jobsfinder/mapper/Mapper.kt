package com.cupcake.jobsfinder.mapper

interface Mapper<I, O> {
    fun mapTo(input: I): O
}