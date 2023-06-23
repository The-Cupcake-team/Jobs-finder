package repo

import com.cupcake.models.Token
import com.cupcake.models.User

interface AuthenticationRepository {
    suspend fun register(
        fullName: String,
        userName: String,
        email: String,
        password: String,
        jobTitleId: Int
    ): User

    suspend fun login(
        userName: String,
        password: String
    ): Token

    suspend fun saveAuthData(token: Token)

    suspend fun getAuthToken(): String?

    suspend fun getAuthTokenExpireTime(): Long?

    suspend fun clearAuthData()
}