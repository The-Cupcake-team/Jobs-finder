package repo

import com.cupcake.models.Job
import com.cupcake.models.JobTitle
import com.cupcake.models.Post
import com.cupcake.models.Profile
import com.cupcake.models.Token
import com.cupcake.models.User
import com.cupcake.models.UserProfile

interface JobFinderRepository {


    // region Job

    suspend fun createJob(jobInfo: Job): Boolean

    suspend fun getJobs(): List<Job>

    suspend fun getAllJobTitles(): List<JobTitle>

    suspend fun getJobById(jobId: String): Job

    suspend fun insertJob(job: Job)

    suspend fun deleteJob(job: Job)

    suspend fun getSavedJobById(id: String): Job?

    //endregion

    // clean up

    // region Post

    suspend fun createPost(content: String): Post

    suspend fun getAllPosts(): List<Post>

    suspend fun getPostById(id: String): Post

    suspend fun getFollowingPosts(): List<Post>

    suspend fun insertPost(post: Post)

    suspend fun deletePost(post: Post)

    suspend fun getSavedPostById(id: String): Post?


    //endregion

    // region DataStore
    suspend fun saveProfileData(avatarUri: String, jobTitle: Int)

    suspend fun getAvatarUri(): String?

    suspend fun getJobTitle(): Int?

    suspend fun clearProfileData()

    //endregion

    // region Profile

    suspend fun insertProfile(user: User)
    suspend fun getProfile(id : String): UserProfile
    // region Profile

}