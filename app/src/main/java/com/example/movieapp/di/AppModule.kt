package com.example.movieapp.di

import com.example.data.local.dao.FavoriteMovieDao
import com.example.data.remote.api.MovieApi
import com.example.data.repository.FavoriteRepositoryImpl
import com.example.data.repository.MovieRepositoryImpl
import com.example.domain.repository.FavoriteRepository
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.favorites.AddFavoriteUseCase
import com.example.domain.usecase.favorites.GetFavoritesIdsUseCase
import com.example.domain.usecase.favorites.GetFavoritesUseCase
import com.example.domain.usecase.favorites.IsFavoriteUseCase
import com.example.domain.usecase.favorites.RemoveFavoriteUseCase
import com.example.domain.usecase.movies.GetMovieByIdUseCase
import com.example.domain.usecase.movies.GetMoviesUseCase
import com.example.domain.usecase.movies.GetMoviesWithFavoritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Provides
    fun provideMovieRepository(api: MovieApi): MovieRepository =
        MovieRepositoryImpl(api)

    @Provides
    fun provideFavoriteRepository(dao: FavoriteMovieDao): FavoriteRepository =
        FavoriteRepositoryImpl(dao)

    @Provides
    fun provideGetMoviesUseCase(repository: MovieRepository): GetMoviesUseCase =
        GetMoviesUseCase(repository)

    @Provides
    fun provideGetMovieWithFavoritesUseCase(
        getMoviesUseCase: GetMoviesUseCase,
        getFavoritesIdsUseCase: GetFavoritesIdsUseCase
    ): GetMoviesWithFavoritesUseCase =
        GetMoviesWithFavoritesUseCase(getMoviesUseCase, getFavoritesIdsUseCase)


    @Provides
    fun provideGetMovieDetailUseCase(repository: MovieRepository): GetMovieByIdUseCase =
        GetMovieByIdUseCase(repository)

    @Provides
    fun provideAddFavoriteUseCase(repository: FavoriteRepository): AddFavoriteUseCase =
        AddFavoriteUseCase(repository)

    @Provides
    fun provideRemoveFavoriteUseCase(repository: FavoriteRepository): RemoveFavoriteUseCase =
        RemoveFavoriteUseCase(repository)

    @Provides
    fun provideIsFavoriteUseCase(repository: FavoriteRepository): IsFavoriteUseCase =
        IsFavoriteUseCase(repository)

    @Provides
    fun provideGetFavoriteMoviesUseCase(repository: FavoriteRepository): GetFavoritesUseCase =
        GetFavoritesUseCase(repository)

    @Provides
    fun provideGetFavoritesIdsUseCase(repository: FavoriteRepository): GetFavoritesIdsUseCase =
        GetFavoritesIdsUseCase(repository)
}