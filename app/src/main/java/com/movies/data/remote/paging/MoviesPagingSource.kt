package com.movies.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.movies.data.remote.mapper.MovieListDTOMapper
import com.movies.data.remote.model.MoviesDTO
import com.movies.data.remote.service.api.ServiceApi
import com.movies.domain.model.MovieDomainModel
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MoviesPagingSource(
    private val service: ServiceApi,
    private val movieDTOMapper: MovieListDTOMapper,
    private val category: String? = null,
    private val search: String? = null
) : PagingSource<Int, MovieDomainModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDomainModel> {
        return try {
            lateinit var response: Response<MoviesDTO>
            val page = params.key ?: 1
            category?.let {
                response = service.getMovies(it, page)
            }
            search?.let {
                response = service.searchMovies(it, page)
            }
            LoadResult.Page(
                data = movieDTOMapper(response.body() ?: throw IllegalArgumentException()),
                prevKey = if (page != 1) page - 1 else null,
                nextKey = page.plus(1)
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: IllegalArgumentException) {
            LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, MovieDomainModel>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}