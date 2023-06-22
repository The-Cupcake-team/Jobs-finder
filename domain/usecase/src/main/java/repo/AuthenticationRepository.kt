package repo

import com.cupcake.models.User

interface AuthenticationRepository {
    suspend fun register(
        fullName: String,
        userName: String,
        email: String,
        password: String
    ): User

    suspend fun saveAuthToken(token: String)

    suspend fun getAuthToken(): String?

    suspend fun clearAuthToken()
}