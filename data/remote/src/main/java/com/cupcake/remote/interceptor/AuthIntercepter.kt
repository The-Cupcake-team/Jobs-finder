package com.cupcake.remote.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .cacheControl(cacheControl)
            .header(AUTHORIZATION, "$BEARER $TOKEN")
            .build()
        return chain.proceed(request)
    }

    private val cacheControl = CacheControl.Builder()
        .maxAge(1, TimeUnit.HOURS)
        .build()


    private companion object {
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "bearer"
         val TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMTMwMmI2MS0wNzQ0LTRjMzYtOWYwZi1lOTEwZWNmNWQwOWYiLCJleHAiOjE2ODc3ODE3MDF9.lCAH6UpYnvjq2kTFpbsRJAMtAYaeVu6JunNxGPu-UIQ"
    }


}