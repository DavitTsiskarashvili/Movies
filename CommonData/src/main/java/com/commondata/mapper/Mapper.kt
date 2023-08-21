package com.commondata.mapper

interface Mapper<in ModelA, out ModelB> {
    operator fun invoke(model: ModelA): ModelB
}