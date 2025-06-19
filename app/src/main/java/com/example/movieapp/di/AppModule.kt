package com.example.movieapp.di

import com.example.data.remote.api.MovieApi
import com.example.data.repository.MovieRepositoryImpl
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetMovieByIdUseCase
import com.example.domain.usecase.GetPopularMoviesUseCase
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
    fun provideGetPopularMoviesUseCase(repository: MovieRepository): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(repository)

    @Provides
    fun provideGetMovieDetailUseCase(repository: MovieRepository): GetMovieByIdUseCase =
        GetMovieByIdUseCase(repository)
}
