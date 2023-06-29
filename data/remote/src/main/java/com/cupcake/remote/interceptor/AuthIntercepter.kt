package com.cupcake.remote.interceptor

import com.cupcake.remote.local.AuthDataStore
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.security.AuthProvider
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val authDataStore: AuthDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .cacheControl(cacheControl)
            .header(AUTHORIZATION, "$BEARER ${authDataStore.getAuthToken()}")
            .build()
        return chain.proceed(request)
    }

    private val cacheControl = CacheControl.Builder()
        .maxAge(1, TimeUnit.HOURS)
        .build()


    private companion object {
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "bearer"
    }


}