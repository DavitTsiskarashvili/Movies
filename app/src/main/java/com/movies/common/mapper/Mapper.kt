package com.space.quiz_app.common.mapper

interface Mapper<in ModelA, out ModelB> {
    operator fun invoke(model: ModelA): ModelB
}