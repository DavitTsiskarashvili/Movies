package com.movies.domain.usecase.base

abstract class BaseUseCase<in Params, out T> {
    abstract suspend operator fun invoke(params: Params? = null): T
}