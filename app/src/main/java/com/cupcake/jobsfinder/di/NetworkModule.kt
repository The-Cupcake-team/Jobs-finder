package com.cupcake.jobsfinder.di

import com.cupcake.jobsfinder.BuildConfig
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

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	fun provideJobApiService(
		okHttpClient: OkHttpClient,
		gsonConverterFactory: GsonConverterFactory
	): JobApiService {
		return Retrofit.Builder()
			.baseUrl(BuildConfig.BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(gsonConverterFactory)
			.build()
			.create(JobApiService::class.java)
	}

	@Provides
	fun provideOkHttpClient(
		authInterceptor: AuthInterceptor,
		loggingInterceptor: HttpLoggingInterceptor
	): OkHttpClient {
		return OkHttpClient().newBuilder()
			.addInterceptor(authInterceptor)
			.addInterceptor(loggingInterceptor)
			.addInterceptor { chain ->
				val originalRequest = chain.request()
				val newRequest = originalRequest.newBuilder()
					.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlZTVhZTBkNS0xNzgzLTRhNWItYWVkMi0zYzcxOWQyY2FmMzQiLCJleHAiOjE2ODc0NTg3MjN9.KuUkrlsOBP75uQq5HDkd-7ZEQZ0_zleiPYOUdV3rPRk")
					.build()
				chain.proceed(newRequest)
			}
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