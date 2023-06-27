package repo

import com.cupcake.models.*
import com.cupcake.models.Comment
import com.cupcake.models.Job
import com.cupcake.models.JobTitle
import com.cupcake.models.Post
import java.io.File

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

    suspend fun createPost(content: String, image: File?): Post

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

    //region Comment
    suspend fun getComments(id: String): List<Comment>

    suspend fun createComment(postId: String, content: String): Boolean
    //endregion

    // region Profile

    suspend fun insertProfile(user: User)
     suspend fun getAllEducations(): List<Education>
    suspend fun addEducation(education: Education)
    suspend fun updateEducation(education: Education)

    suspend fun getProfile(): UserProfile
    // region Profile

    suspend fun getAllSavedPosts(): List<Post>

    suspend fun getAllUserPost():List<Post>
    suspend fun getSavedJobs(): List<Job>
    suspend fun getRecentJobs(): List<Job>

    suspend fun getAllSkills(): List<Skill>
    suspend fun deleteSkills(id : String)

}