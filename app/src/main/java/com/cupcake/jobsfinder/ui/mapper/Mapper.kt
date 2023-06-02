package com.cupcake.jobsfinder.ui.mapper

interface Mapper<I, O> {
    fun mapTo(input: I): O
}