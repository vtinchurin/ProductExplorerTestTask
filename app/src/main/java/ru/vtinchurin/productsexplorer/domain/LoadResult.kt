package ru.vtinchurin.productsexplorer.domain

interface LoadResult<D : Any> {

    interface Mapper<Inner: Any,Result : Any> {
        fun mapSuccess(data: Inner): Result
        fun mapError(message: String):Result
        fun mapEmpty():Result
    }

    fun<R: Any> map(mapper: Mapper<D,R>):R

    data class Success<D : Any>(private val data: D) : LoadResult<D>{
        override fun <R : Any> map(mapper: Mapper<D, R>) =
            mapper.mapSuccess(data)
    }
    class Empty<D: Any> : LoadResult<D> {
        override fun <R : Any> map(mapper: Mapper<D, R>): R =
            mapper.mapEmpty()
    }

    class Error<D: Any>(private val message: String): LoadResult<D>{
        override fun <R : Any> map(mapper: Mapper<D, R>) =
            mapper.mapError(message)
    }

}
