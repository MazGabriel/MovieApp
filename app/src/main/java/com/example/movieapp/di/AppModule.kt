package com.example.movieapp.di

import com.example.data.local.dao.FavoriteMovieDao
import com.example.data.remote.api.MovieApi
import com.example.data.repository.FavoriteRepositoryImpl
import com.example.data.repository.MovieRepositoryImpl
import com.example.domain.repository.FavoriteRepository
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.favorites.AddFavoriteUseCase
import com.example.domain.usecase.favorites.IsFavoriteUseCase
import com.example.domain.usecase.favorites.RemoveFavoriteUseCase
import com.example.domain.usecase.movies.GetMovieByIdUseCase
import com.example.domain.usecase.movies.GetPopularMoviesUseCase
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
    fun provideGetPopularMoviesUseCase(repository: MovieRepository): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(repository)

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
}