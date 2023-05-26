package com.cupcake.jobsfinder.di

import com.cupcake.jobsfinder.data.remote.JobApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	fun provideJobApiService(
		okHttpClient: OkHttpClient,
		gsonConverterFactory: GsonConverterFactory
	): JobApiService {
		return Retrofit.Builder().baseUrl("")
			.client(okHttpClient)
			.addConverterFactory(gsonConverterFactory)
			.build()
			.create(JobApiService::class.java)
	}

	@Provides
	fun provideOkHttpClient(
		loggingInterceptor: HttpLoggingInterceptor
	): OkHttpClient {
		return OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build()
	}

	@Provides
	fun provideLoggingInterceptor(): HttpLoggingInterceptor {
		return HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		}
	}

	@Provides
	fun provideGsonConverterFactory(): GsonConverterFactory {
		return GsonConverterFactory.create()
	}
}