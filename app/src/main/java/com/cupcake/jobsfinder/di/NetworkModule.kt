package com.cupcake.jobsfinder.di

import com.cupcake.jobsfinder.BuildConfig
import com.cupcake.remote.AuthApiService
import com.cupcake.remote.JobApiService
import com.cupcake.remote.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	fun provideJobApiService(
		@Named("OkHttpClient") okHttpClient: OkHttpClient,
		gsonConverterFactory: GsonConverterFactory
	): JobApiService {
		return retrofitBuilderJobFinder(okHttpClient, gsonConverterFactory)
			.create(JobApiService::class.java)
	}

	@Provides
	fun provideAuthApiService(
		@Named("AuthOkHttpClient") okHttpClient: OkHttpClient,
		gsonConverterFactory: GsonConverterFactory
	): AuthApiService {
		return retrofitBuilderJobFinder(okHttpClient, gsonConverterFactory)
			.create(AuthApiService::class.java)
	}

	private fun retrofitBuilderJobFinder(
		okHttpClient: OkHttpClient,
		gsonConverterFactory: GsonConverterFactory
	): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BuildConfig.BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(gsonConverterFactory)
			.build()
	}

	@Provides
	@Named("AuthOkHttpClient")
	fun provideAuthOkHttpClient(
		loggingInterceptor: HttpLoggingInterceptor
	): OkHttpClient {
		return OkHttpClient().newBuilder()
			.addInterceptor(loggingInterceptor)
			.build()
	}

	@Provides
	@Named("OkHttpClient")
	fun provideOkHttpClient(
		authInterceptor: AuthInterceptor,
		loggingInterceptor: HttpLoggingInterceptor
	): OkHttpClient {
		return OkHttpClient().newBuilder()
			.addInterceptor(authInterceptor)
			.addInterceptor(loggingInterceptor)
			.build()
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